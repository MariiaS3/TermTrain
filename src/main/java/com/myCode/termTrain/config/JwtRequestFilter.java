package com.myCode.termTrain.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    private JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        //intercept token from request 
        String tokenWithBearer = request.getHeader("Authorization");

        if(tokenWithBearer==null || !tokenWithBearer.startsWith("Bearer")){ // chek if token is null or without Bearer
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenWithBearer.substring(7);//cut Bearer from token

        // set authentication inside a security context 
        Authentication authentication =  jwtUtil.validateToken(token); 

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        filterChain.doFilter(request, response);
    }
    
    
}
