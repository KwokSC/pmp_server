package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.User;
import com.chunkie.pmp_server.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public ResponseObj createAccount(@RequestBody User user){
        return new ResponseObj();
    }
}