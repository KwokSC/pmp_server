package com.chunkie.pmp_server.service;

import com.chunkie.pmp_server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    
}
