package com.chunkie.pmp_server.common;

import lombok.Data;

@Data
public class ResponseObj {

    private Object data;

    private int code;

    private String msg;

}
