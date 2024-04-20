package com.example.magazine.modules.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {

    private final String Secret="myHS256";
    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + (60*60*24*1000)))
                .signWith(SignatureAlgorithm.HS256, Secret)
                .compact();
    }

    public String getUsername(String token){
        return Jwts.parser().setSigningKey(Secret).parseClaimsJws(token).getBody().getSubject();
    }
}
