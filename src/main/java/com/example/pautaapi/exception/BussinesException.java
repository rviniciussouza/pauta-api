package com.example.pautaapi.exception;

import org.springframework.http.HttpStatus;

public class BussinesException extends RuntimeException {
    public BussinesException(String msg) {
        super(msg);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}