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

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public ResponseObj createAccount(@RequestBody User user){
        ResponseObj responseObj = new ResponseObj();
        int result = userService.createAccount(user);
        if (result!=0){
            responseObj.setCode(Constants.Code.NORMAL);
            responseObj.setMsg(Constants.Msgs.SUCCESS);
            responseObj.setData("Create an account successfully");
        }else {
            responseObj.setCode(Constants.Code.EXCEPTION);
            responseObj.setMsg(Constants.Msgs.FAIL);
            responseObj.setData("Fail to create an account");
        }
        return  responseObj;
    }

    @RequestMapping("/login")
    public ResponseObj login(@RequestBody User user){
        String authToken = userService.authenticateUser(user);
        ResponseObj responseObj = new ResponseObj();
        if(!authToken.isEmpty()){
        responseObj.setCode(Constants.Code.NORMAL);
        responseObj.setMsg(Constants.Msgs.SUCCESS);
        responseObj.setData(authToken);
    }else {
        responseObj.setCode(Constants.Code.EXCEPTION);
        responseObj.setMsg(Constants.Msgs.FAIL);
    }
        return responseObj;
    }
}