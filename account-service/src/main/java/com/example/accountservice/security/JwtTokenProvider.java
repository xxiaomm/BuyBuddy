package com.example.accountservice.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    /**
     * generate token
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();

        return token;
    }

    /**
     * get username from the token
     * @param token
     * @return
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret) // .build().parseSignedClaims
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    /**
     * validate JWT token
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
//        }catch (SignatureException ex){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
