package com.myCode.termTrain.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.myCode.termTrain.service.AccountDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String SECRET_KEY = "secretKeySignWithHS256AlgorithmAndSecretKey";
    private final  AccountDetailService userDetailService;

    public JwtUtil(AccountDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername() );
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // time of expire
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                //encode data using secret_key and HS256, and it only decode by secret_key 
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public Authentication validateToken(String token){
        Claims claims = extractClaims(token);
        if(claims.getExpiration().before(new Date())){  //check if tocken did not expire
            return null;
        }

        String username = claims.getSubject();
        try{
            UserDetails user = userDetailService.loadUserByUsername(username);
        }catch(Exception ex){
            return null;
        }

        return new UsernamePasswordAuthenticationToken( username, null, new ArrayList<>());

    }

    private Claims extractClaims(String token){
       return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
