package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.common.Constants;
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
        int result = userMapper.addUser(user);
        if (result!=0){
            String authToken = authService.generateToken(user.getUserAccount());
            return authToken;
        }else {
            return "";
        }
    }

    public String authenticateUser(User user){
        User u = userMapper.selectByAccount(user.getUserAccount());
        if (u != null && u.getUserPassword().equals(user.getUserPassword())){
            String authToken = authService.generateToken(user.getUserAccount());
            return authToken;
        }else
            return "";
    }

}