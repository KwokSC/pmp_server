package com.chunkie.pmp_server.entity;

import com.chunkie.pmp_server.common.Gender;
import com.chunkie.pmp_server.common.Species;
import lombok.Data;

import java.util.Date;

@Data
public class Profile {

    private String profileName;

    private String userId;

    private Species profileSpecies;

    private Gender profileGender;

    private String profileDescription;

    private int profileAge;

    private Date profileBirth;

    private float profileLatitude;

    private float profileLongitude;
}