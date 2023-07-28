package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.AuthInfo;
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

    public AuthInfo createAccount(User user){
        AuthInfo authInfo = new AuthInfo();
        if (userMapper.addUser(user)!=0){
            authInfo.setUserId(user.getUserId());
            authInfo.setToken(authService.generateToken(user.getUserId()));
            authInfo.setActivated(0);
            return authInfo;
        }else {
            return null;
        }
    }

    public AuthInfo authenticateUser(User user){
        User u = userMapper.selectByAccount(user.getUserAccount());
        AuthInfo authInfo = new AuthInfo();
        if (u != null && u.getUserPassword().equals(user.getUserPassword())){
            authInfo.setUserId(u.getUserId());
            authInfo.setToken(authService.generateToken(u.getUserId()));
            authInfo.setActivated(u.getUserActivate());
            return authInfo;
        }else
            return null;
    }

}