package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.User;
import com.chunkie.pmp_server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthService authService;

    public String createAccount(User user){
        if (userMapper.addUser(user)!=0){
            return authService.generateToken(user.getUserId());
        }else {
            return "";
        }
    }

    public String authenticateUser(User user){
        User u = userMapper.selectByAccount(user.getUserAccount());
        if (u != null && u.getUserPassword().equals(user.getUserPassword())){
            return authService.generateToken(u.getUserId());
        }else
            return "";
    }

}