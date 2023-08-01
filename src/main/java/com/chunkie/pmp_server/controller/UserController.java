package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.AuthInfo;
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
    /**
     * @Description:
     * @Param user
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj createAccount(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        AuthInfo authInfo = userService.createAccount(user);
        return !(authInfo == null) ? new ResponseObj(authInfo, Constants.Code.NORMAL, Constants.Msg.SUCCESS) : new ResponseObj("Fail to create an account", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
    }

    @RequestMapping("/login")
    /**
     * @Description:
     * @Param user
     * @Return {@link com.chunkie.pmp_server.common.ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj login(@RequestBody User user){
        AuthInfo authInfo = userService.authenticateUser(user);
        return !(authInfo == null) ? new ResponseObj(authInfo, Constants.Code.NORMAL, Constants.Msg.SUCCESS) : new ResponseObj("Fail to login", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
    }
}