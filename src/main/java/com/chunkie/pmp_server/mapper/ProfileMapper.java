package com.chunkie.pmp_server.mapper;

import com.chunkie.pmp_server.entity.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ProfileMapper {

    int updateLocation(@Param("userId") String userId,
                       @Param("latitude") float latitude,
                       @Param("longitude") float longitude);
}
