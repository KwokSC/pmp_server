package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.annotation.LoginRequired;
import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.Location;
import com.chunkie.pmp_server.service.ProfileService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Resource
    private ProfileService profileService;

    @LoginRequired
    @RequestMapping("/updateLocation")
    public ResponseObj updateLocation(@RequestBody Location location,
                                      HttpServletRequest request) {
        if (profileService.updateLocation(request.getHeader("Authorization"), location.getLatitude(), location.getLongitude()) != 0) {
            return new ResponseObj("Successfully update location", Constants.Code.NORMAL, Constants.Msgs.SUCCESS);
        }else {
            return new ResponseObj("Fail to update location", Constants.Code.EXCEPTION, Constants.Msgs.FAIL);
        }
    }

    @LoginRequired
    @RequestMapping("/getProfilePhotos")
    public ResponseObj getProfilePhotos(HttpServletRequest request) throws IOException {
        List<String> photos = profileService.getProfilePhotos(request.getHeader("Authorization"));
        return new ResponseObj(photos, Constants.Code.NORMAL, Constants.Msgs.SUCCESS);
    }

}
