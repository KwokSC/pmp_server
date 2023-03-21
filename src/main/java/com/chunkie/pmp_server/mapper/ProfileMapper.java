package com.chunkie.pmp_server.mapper;

import com.chunkie.pmp_server.entity.Profile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileMapper {

    int updateLocation(float latitude, float longitude);
}
