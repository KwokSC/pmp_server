package com.chunkie.pmp_server.controller;

import com.chunkie.pmp_server.annotation.Idempotent;
import com.chunkie.pmp_server.annotation.LoginRequired;
import com.chunkie.pmp_server.common.Constants;
import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.entity.Profile;
import com.chunkie.pmp_server.service.IdempotentService;
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

    @Resource
    private IdempotentService idempotentService;

    @RequestMapping("/createProfile")
    @Idempotent(prefix = "createProfile", expiration = 30)
    @LoginRequired
    /**s
     * @Description:
     * @Param profile
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj createProfile(@RequestBody Profile profile,
                                     HttpServletRequest request) {
        idempotentService.generateToken("createProfile", 30);
        if (profileService.createProfile(request.getHeader("Authorization"), profile) != 0) {
            return new ResponseObj("Successfully update location", Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj("Fail to update location", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
        }
    }

    @RequestMapping("/updateLocation")
    @LoginRequired
    /**
     * @Description:
     * @Param location
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj updateLocation(@RequestParam(value = "latitude") float latitude,
                                      @RequestParam(value = "longitude") float longitude,
                                      HttpServletRequest request) {
        if (profileService.updateLocation(request.getHeader("Authorization"), latitude, longitude) != 0) {
            return new ResponseObj("Successfully update location", Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj("Fail to update location", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
        }
    }

    @RequestMapping("/uploadPhoto")
    @LoginRequired
    /**
     * @Description:
     * @Param photo
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj uploadPhoto(@RequestParam(value = "photo")MultipartFile photo, HttpServletRequest request) throws IOException {
        if (profileService.uploadPhoto(photo, request.getHeader("Authorization"))){
            return new ResponseObj("Successfully upload a photo", Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj("Fail to upload", Constants.Code.EXCEPTION, Constants.Msg.FAIL);
        }
    }

    @RequestMapping("/uploadPhotos")
    @Idempotent(prefix = "uploadPhotos")
    @LoginRequired
    /**
     * @Description:
     * @Param photos
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj uploadPhotos(@RequestParam(value = "photos")List<MultipartFile> photos, HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        idempotentService.generateToken("uploadPhotos", 60);
        return new ResponseObj(profileService.uploadPhotos(photos, authToken), Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

    @RequestMapping("/getProfilePhotos")
    @LoginRequired
    /**
     * @Description:
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj getProfilePhotos(HttpServletRequest request) {
        List<String> photos = profileService.getProfilePhotos(request.getHeader("Authorization"));
        return new ResponseObj(photos, Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

    @RequestMapping("/getProfilePhotosById")
    /**
     * @Description:
     * @Param userId
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj getProfilePhotosById(@RequestParam(value = "id")String userId){
        List<String> photos = profileService.getProfilePhotosById(userId);
        return new ResponseObj(photos, Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

    @RequestMapping("/getProfileById")
    /**
     * @Description:
     * @Param userId
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj getProfileById(@RequestParam(value = "id") String userId){
        return new ResponseObj(profileService.getProfileById(userId), Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

    @RequestMapping("/getSelfProfile")
    @LoginRequired
    /**
     * @Description:
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj getSelfProfile(HttpServletRequest request){
        return new ResponseObj(profileService.getSelfProfile(request.getHeader("Authorization")), Constants.Code.NORMAL, Constants.Msg.SUCCESS);
    }

    @RequestMapping("/isNewUser")
    @LoginRequired
    /**
     * @Description:
     * @Param request
     * @Return {@link ResponseObj}
     * @Author: Sicheng
     * @Date: 2023/8/1
    **/
    public ResponseObj isNewUser(HttpServletRequest request){
        if (profileService.getSelfProfile(request.getHeader("Authorization"))==null){
            return new ResponseObj(true, Constants.Code.NORMAL, Constants.Msg.SUCCESS);
        }else {
            return new ResponseObj(false, Constants.Code.NORMAL, Constants.Msg.NEW);
        }
    }

}
