package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.mapper.ProfileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProfileService {

    @Resource
    private ProfileMapper profileMapper;

    public int updateLocation(float latitude, float longitude){
        return profileMapper.updateLocation(latitude, longitude);
    }


}
