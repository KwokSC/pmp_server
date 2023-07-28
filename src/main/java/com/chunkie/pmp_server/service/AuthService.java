package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.common.Constants;
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
                .compact();
        stringRedisTemplate.opsForValue().set(authToken, id, Duration.ofMinutes(Constants.Auth.EXP));
        return authToken;
    }

    public Boolean isTokenValid(String token){
        return token != null && stringRedisTemplate.opsForValue().get(token) != null;
    }

    public String getUserByToken(String token){
        return stringRedisTemplate.opsForValue().get(token);
    }
}
