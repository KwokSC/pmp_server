package com.chunkie.pmp_server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Profile {

    private String profileName;

    private String profileGender;

    private String profileDescription;

    private int profileAge;

    private Date profileBirth;

    private String profileBreed;

    private String profileGoal;
}