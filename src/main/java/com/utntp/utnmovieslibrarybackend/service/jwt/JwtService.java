package com.utntp.utnmovieslibrarybackend.service.jwt;

import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.enums.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private Key getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateToken(String email, UserRoleEnum role, Long userId, String username){
        return Jwts.builder()
                .subject(email)
                .claim("role", role.name())
                .claim("userId", userId)
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token){return  getClaims(token).get("username", String.class);}

    public String getEmail(String token){
        return getClaims(token).getSubject();
    }

    public UserRoleEnum getRole(String token){
        String role = getClaims(token).get("role", String.class);
        return UserRoleEnum.valueOf(role);
    }

    public Long getUserId(String token){
        return getClaims(token).get("userId",Long.class);
    }

    public boolean isTokenValid(String token){
        try{
            Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserResponse getUserData(String token){
        return new UserResponse(this.getUserId(token), this.getUsername(token), this.getEmail(token), this.getRole(token).toString());
    }
}
