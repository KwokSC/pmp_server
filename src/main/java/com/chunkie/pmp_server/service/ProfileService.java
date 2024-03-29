package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.entity.Profile;
import com.chunkie.pmp_server.mapper.ProfileMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ProfileService {

    @Resource
    private AuthService authService;

    @Resource
    private ProfileMapper profileMapper;

    @Resource
    private S3Service s3Service;

    public int createProfile(String authToken, Profile profile) {
        profile.setUserId(authService.getUserByToken(authToken));
        return profileMapper.createProfile(profile);
    }

    public int updateLocation(String authToken, float latitude, float longitude) {
        return profileMapper.updateLocation(authService.getUserByToken(authToken), latitude, longitude);
    }

    public List<Integer> uploadPhotos(List<MultipartFile> photos, String authToken) {
        String userId = authService.getUserByToken(authToken);
        return s3Service.uploadMultiplePhotos(photos, userId);
    }

    public boolean uploadPhoto(MultipartFile photo, String authToken) {
        String userId = authService.getUserByToken(authToken);
        return s3Service.uploadPhoto(photo, userId);
    }

    public List<String> getProfilePhotos(String authToken) {
        String userId = authService.getUserByToken(authToken);
        return s3Service.getPhotos(userId);
    }

    public List<String> getProfilePhotosById(String userId) {
        return s3Service.getPhotos(userId);
    }

    public Profile getProfileById(String userId) {
        return profileMapper.getProfileById(userId);
    }

    public Profile getSelfProfile(String authToken) {
        return profileMapper.getProfileById(authService.getUserByToken(authToken));
    }
}
