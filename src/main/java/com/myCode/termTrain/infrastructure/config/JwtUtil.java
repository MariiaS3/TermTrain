package com.myCode.termTrain.infrastructure.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.myCode.termTrain.domain.user.core.dto.UserDto;
import com.myCode.termTrain.domain.user.core.service.query.UserQueryService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String SECRET_KEY = "secretKeySignWithHS256AlgorithmAndSecretKey";
    private final UserQueryService userQueryService;

    public JwtUtil(UserQueryService userDetailService) {
        this.userQueryService = userDetailService;
    }

    public String generateToken(UserDto userDetails){
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
            UserDto user = userQueryService.verifyUserByUsername(username);
        }catch(Exception ex){
            return null;
        }

        return new UsernamePasswordAuthenticationToken( username, null, new ArrayList<>());

    }

    private Claims extractClaims(String token){
       return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
