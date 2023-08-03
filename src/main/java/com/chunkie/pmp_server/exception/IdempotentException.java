package com.chunkie.pmp_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IdempotentException extends RuntimeException{

    public IdempotentException(){
        super("Repeated request forbidden");
    }

}
