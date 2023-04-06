package com.chunkie.pmp_server.entity;

import lombok.Data;

import java.util.List;

@Data
public class Preference {

    private String userId;

    private List<String> preferredSpecies;

    private int ageMax;

    private int ageMin;

    private int distance;

}
