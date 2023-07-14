package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.annotation.LoginRequired;
import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.Location;
import com.chunkie.pmp_server.entity.Profile;
import com.chunkie.pmp_server.service.ProfileService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Resource
    private ProfileService profileService;


    @RequestMapping("/createProfile")
    @LoginRequired
    public ResponseObj createProfile(@RequestBody Profile profile,
                                     HttpServletRequest request) {
        if (profileService.createProfile(request.getHeader("Authorization"), profile) != 0) {
            return new ResponseObj("Successfully update location", Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj("Fail to update location", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
        }
    }


    @RequestMapping("/updateLocation")
    @LoginRequired
    public ResponseObj updateLocation(@RequestBody Location location,
                                      HttpServletRequest request) {
        if (profileService.updateLocation(request.getHeader("Authorization"), location.getLatitude(), location.getLongitude()) != 0) {
            return new ResponseObj("Successfully update location", Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj("Fail to update location", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
        }
    }


    @RequestMapping("/uploadPhoto")
    @LoginRequired
    public ResponseObj uploadPhoto(@RequestParam(value = "photo")MultipartFile photo, HttpServletRequest request) throws IOException {
        if (profileService.uploadPhoto(photo, request.getHeader("Authorization"))){
            return new ResponseObj("Successfully upload a photo", Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj("Fail to upload", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
        }
    }


    @RequestMapping("/uploadMultiplePhotos")
    @LoginRequired
    public ResponseObj uploadMultiplePhotos(){
        return new ResponseObj();
    }


    @RequestMapping("/getProfilePhotos")
    @LoginRequired
    public ResponseObj getProfilePhotos(HttpServletRequest request) {
        List<String> photos = profileService.getProfilePhotos(request.getHeader("Authorization"));
        return new ResponseObj(photos, Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

    @RequestMapping("/getProfilePhotosById")
    public ResponseObj getProfilePhotosById(@RequestParam(value = "id")String userId){
        return new ResponseObj(profileService.getProfilePhotosById(userId), Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

}
