package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.annotation.LoginRequired;
import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.service.AuthService;
import com.chunkie.pmp_server.service.MatchService;
import com.chunkie.pmp_server.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Resource
    private MatchService matchService;

    @Resource
    private ProfileService profileService;

    @Resource
    private AuthService authService;

    @LoginRequired
    @RequestMapping("/getMatch")
    public ResponseObj getMatch(){
        return new ResponseObj(null, Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }
}
