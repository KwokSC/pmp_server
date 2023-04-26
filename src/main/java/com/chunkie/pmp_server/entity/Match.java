package com.chunkie.pmp_server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Match {

    private Long matchId;

    private String matcherId;

    private String targetId;

    private Date matchDate;

}