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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping("/getMatch")
    @LoginRequired
    /**
     * @Description: 
     * @Param request
     * @Return: {@link ResponseObj}
     * @Author: chunkie
     * @Date: 8/5/23
     */
    public ResponseObj getMatch(HttpServletRequest request){
        List<String> matchList = new ArrayList<>();

        return new ResponseObj(matchList, Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }
}
