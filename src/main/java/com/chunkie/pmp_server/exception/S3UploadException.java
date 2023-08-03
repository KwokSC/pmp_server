package com.chunkie.pmp_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class S3UploadException extends RuntimeException{

    public S3UploadException(){
        super();
    }
}
