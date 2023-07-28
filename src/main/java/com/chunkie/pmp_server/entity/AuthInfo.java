package com.chunkie.pmp_server.entity;

import com.chunkie.pmp_server.common.Constants;
import lombok.Data;

@Data
public class AuthInfo {

    private String token;

    private long expiration = Constants.Auth.EXP;

    private String userId;

    private int activated;
}
