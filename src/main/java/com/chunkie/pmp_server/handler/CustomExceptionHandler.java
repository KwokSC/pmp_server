package com.chunkie.pmp_server.handler;

import com.chunkie.pmp_server.common.ResponseObj;
import com.chunkie.pmp_server.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseObj> handleUnauthorizedException(UnauthorizedException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObj(null, HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
}
