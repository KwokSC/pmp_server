package com.chunkie.pmp_server.mapper;

import com.chunkie.pmp_server.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByAccount(String account);

    int addUser(User user);
}
