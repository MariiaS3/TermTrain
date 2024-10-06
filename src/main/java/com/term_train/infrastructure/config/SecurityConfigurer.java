package com.term_train.infrastructure.config;

import com.term_train.infrastructure.util.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfigurer { // extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountDetailService accountDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(accountDetailService);
        authProvider.setPasswordEncoder(PasswordEncoderConfig.passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                  .cors().configurationSource(request -> {
                      var cosrConf = new CorsConfiguration();
                      cosrConf.addAllowedHeader("*");
                      cosrConf.addAllowedMethod("*");
                      cosrConf.addAllowedOrigin("*");
                      return cosrConf;
                  })
                .and()
                 .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/v1/login", "/api/v1/register", "/api/v1/**", "/h2-console/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurer {
//
//    private final AccountDetailService accountDetailService;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtRequestFilter jwtRequestFilter;
//
//
//
//    public SecurityConfigurer(AccountDetailService accountDetailService, PasswordEncoder passwordEncoder,
//            JwtRequestFilter jwtRequestFilter) {
//        this.accountDetailService = accountDetailService;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtRequestFilter = jwtRequestFilter;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder);
//        accountDetailService.addUser("username");
//        return authenticationManagerBuilder.build();
//    }
//
//    private AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(accountDetailService);
//        return provider;
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }
//
//    @Bean
//    protected HttpSecurity configure(HttpSecurity http) throws Exception {
//        return http
//                .cors().configurationSource(corsConfigurationSource()).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/api/v1/login", "/api/v1/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//}
//
//



