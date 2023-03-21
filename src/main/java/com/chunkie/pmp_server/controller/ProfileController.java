package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.service.ProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Resource
    private ProfileService profileService;

    @RequestMapping("updateLocation")
    public ResponseObj updateLocation(@RequestParam("latitude") float latitude,
                                      @RequestParam("longitude") float longitude) {
        if (profileService.updateLocation(latitude, longitude) != 0) {
            return new ResponseObj("Successfully update location", Constants.Code.NORMAL, Constants.Msgs.SUCCESS);
        }else {
            return new ResponseObj("Fail to update location", Constants.Code.EXCEPTION, Constants.Msgs.FAIL);
        }
    }
}
