package com.chunkie.pmp_server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private Long messageId;

    private String sender;

    private String recipient;

    private Date sentAt;

    private String message;
}
