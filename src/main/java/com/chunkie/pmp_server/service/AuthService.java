package com.chunkie.pmp_server.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class AuthService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String generateToken(String account){
        String authToken = Jwts.builder()
                .setSubject(account)
                .signWith(SignatureAlgorithm.HS256, "secret-key")
                .compact();;
        stringRedisTemplate.opsForValue().set(authToken, account);
        return authToken;
    }

    public String getUserByToken(String token){
        return stringRedisTemplate.opsForValue().get(token);
    }
}
