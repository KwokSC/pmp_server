package com.chunkie.pmp_server.mapper;

import com.chunkie.pmp_server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    User selectByAccount(String account);

    int addUser(User user);
}
