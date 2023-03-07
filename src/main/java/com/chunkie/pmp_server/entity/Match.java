package com.chunkie.pmp_server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Match {

    private Long matchId;

    private String userOneId;

    private String UserTwoId;

    private Date matchDate;

}
