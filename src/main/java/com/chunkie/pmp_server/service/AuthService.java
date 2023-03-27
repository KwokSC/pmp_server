package com.chunkie.pmp_server.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

@Service
public class AuthService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String generateToken(String id){
        String authToken = Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS256, "secret-key")
                .compact();;
        stringRedisTemplate.opsForValue().set(authToken, id, Duration.ofMinutes(30));
        return authToken;
    }


    public String getUserByToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey("secret-key")
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
