package com.myCode.termTrain.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.myCode.termTrain.service.AccountDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

    private final AccountDetailService accountDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtRequestFilter jwtRequestFilter;



    public SecurityConfigurer(AccountDetailService accountDetailService, PasswordEncoder passwordEncoder,
            JwtRequestFilter jwtRequestFilter) {
        this.accountDetailService = accountDetailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // //manages the authentication configuration
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //In this builder object, we configure the security information for user authentication.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
        auth.authenticationProvider(authenticationProvider());
    }

    private AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // to load details about the user during authentication.
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(accountDetailService);
        return provider;
    }
    // //we can use the HttpSecuirty parameter object to set the authorization of URLs.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disabled cross-site request forgery(csrf) protection because i use jwt if i was not using jwt i would have to enable csrf
        http.csrf().disable() 
        .cors().configurationSource(request ->{
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
            corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
            ));
            corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
            return corsConfiguration;
        }).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        //adds a filter before the position of the specified filter class.
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class )
        //restricting access , public access, that is, anyone can access the PUBLIC_URL("/api/v1/login") endpoint without authentication.
        .authorizeRequests().antMatchers("/api/v1/login").permitAll()  
        .antMatchers("/api/v1/register").permitAll()
        //others endpoints with authentication
        .anyRequest().authenticated();
    }
    
}





