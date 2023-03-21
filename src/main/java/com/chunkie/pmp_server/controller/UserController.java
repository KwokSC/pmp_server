package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.User;
import com.chunkie.pmp_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public ResponseObj createAccount(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        String authToken = userService.createAccount(user);
        return !authToken.isEmpty() ? new ResponseObj(authToken, Constants.Code.NORMAL, Constants.Msgs.SUCCESS) : new ResponseObj("Fail to create an account", Constants.Code.EXCEPTION, Constants.Msgs.FAIL);
    }

    @RequestMapping("/login")
    public ResponseObj login(@RequestBody User user){
        String authToken = userService.authenticateUser(user);
        return !authToken.isEmpty() ? new ResponseObj(authToken, Constants.Code.NORMAL, Constants.Msgs.SUCCESS) : new ResponseObj("Fail to login", Constants.Code.EXCEPTION, Constants.Msgs.FAIL);
    }
}