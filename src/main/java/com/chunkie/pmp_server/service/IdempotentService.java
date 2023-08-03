package com.chunkie.pmp_server.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class IdempotentService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String generateToken(String prefix, int expiration) {
        String token = UUID.randomUUID().toString();
        setIdempotentValue(prefix + token, "", expiration);
        return token;
    }

    public boolean validateToken(String prefix, String token) {
        String key = prefix + token;
        return getIdempotentValue(key) != null;
    }

    public void setIdempotentValue(String key, String value, long expiration) {
        stringRedisTemplate.opsForValue().set(key, value, expiration, TimeUnit.SECONDS);
    }

    public String getIdempotentValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
