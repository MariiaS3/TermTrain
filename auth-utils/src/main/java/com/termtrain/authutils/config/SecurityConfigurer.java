package com.termtrain.authutils.config;

import com.termtrain.authutils.service.AccountDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {

    private final AccountDetailService accountDetailService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfigurer(AccountDetailService accountDetailService, PasswordEncoder passwordEncoder) {
        this.accountDetailService = accountDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    // //manages the authentication configuration
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // to load details about the user during authentication.
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(accountDetailService);
        return provider;
    }

    // //we can use the HttpSecuirty parameter object to set the authorization of URLs.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("__________________________________ filterChain -------------------------------------------------------");
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/test").permitAll()
                        .requestMatchers("/hello").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).formLogin((form) -> form
                        .loginPage("/login").permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

}





