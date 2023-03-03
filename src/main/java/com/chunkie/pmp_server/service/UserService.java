package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.User;
import com.chunkie.pmp_server.mapper.UserMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthService authService;

    public String authenticateUser(User user){
        User u = userMapper.selectByAccount(user.getUserAccount());
        if (u.getUserPassword().equals(user.getUserPassword())){
            String authToken = authService.generateToken(user.getUserAccount());
            return authToken;
        }else
            return null;
    }

}