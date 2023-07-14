package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;

@Service
public class AuthService {

    private static final long EXPIRATION_TIME_MS = 3600000;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String generateToken(String id) {
        Date expiration = new Date(new Date().getTime() + EXPIRATION_TIME_MS);

        String authToken = Jwts.builder()
                .setSubject(id)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "secret-key")
                .compact();
        stringRedisTemplate.opsForValue().set(authToken, id, Duration.ofMinutes(30));
        return authToken;
    }


    public String getUserByToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        }


        Claims claims = Jwts.parser()
                .setSigningKey("secret-key")
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }
}
