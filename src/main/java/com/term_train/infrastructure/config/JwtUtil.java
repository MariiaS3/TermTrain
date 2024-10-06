package com.term_train.infrastructure.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.term_train.ddd.user.domain.dto.AccountDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil  {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${termtrain.app.jwtSecret}")
    private String jwtSecret;

    @Value("${termtrain.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(AccountDto userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOG.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOG.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOG.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOG.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

        public String generateToken(AccountDto userDetails){
        Map<String,Object> claims = new HashMap<>();
        String token = createToken(claims, userDetails.getUsername() );
        return token;
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // time of expire
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                //encode data using secret_key and HS256, and it only decode by secret_key
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}

//    private String SECRET_KEY = "secretKeySignWithHS256AlgorithmAndSecretKey";
//    private final UserQueryService userQueryService;
//
//    public JwtUtil(UserQueryService userDetailService) {
//        this.userQueryService = userDetailService;
//    }
//
//
//    public Authentication validateToken(String token){
//        Claims claims = extractClaims(token);
//        if(claims.getExpiration().before(new Date())){  //check if tocken did not expire
//            return null;
//        }
//
//        String username = claims.getSubject();
//        try{
//            UserDto user = userQueryService.verifyUserByUsername(username);
//        }catch(Exception ex){
//            return null;
//        }
//
//        return new UsernamePasswordAuthenticationToken( username, null, new ArrayList<>());
//
//    }
//
//    private Claims extractClaims(String token){
//       return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//}
