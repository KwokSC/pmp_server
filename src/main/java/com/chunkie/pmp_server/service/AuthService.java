package com.chunkie.pmp_server.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AuthService {

    @Resource
    private StringRedisTemplate redisTemplate;

    public String generateToken(String account){
        String authToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(authToken, account);
        return authToken;
    }

    public boolean validateToken(String token){
        return redisTemplate.opsForValue().get(token) != null;
    }
}
