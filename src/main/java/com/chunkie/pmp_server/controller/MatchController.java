package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.annotation.LoginRequired;
import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.Profile;
import com.chunkie.pmp_server.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Resource
    private MatchService matchService;

    @LoginRequired
    @RequestMapping("/getMatch")
    public ResponseObj getMatch(){

        return new ResponseObj(matchService.getMatch(), Constants.Code.NORMAL, Constants.Msgs.SUCCESS);
    }
}
