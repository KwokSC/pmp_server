package com.chunkie.pmp_server.entity;

import lombok.Data;

@Data
public class User {

    private int userId;

    private String userAccount;

    private String userEmail;

    private String userPhone;

    private String userPassword;

    private int userActivate;
}
