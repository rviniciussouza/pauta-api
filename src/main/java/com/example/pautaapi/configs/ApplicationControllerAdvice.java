package com.example.pautaapi.configs;

import com.example.pautaapi.exception.ApiErrors;
import com.example.pautaapi.exception.BussinesException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    private Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationExcepetion(MethodArgumentNotValidException ex) {
        this.logger.error(ex.getMessage(), ex);
        return new ApiErrors(ex.getBindingResult());
    }

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<ApiErrors> handleBussinesExcepetion(BussinesException ex) {
        this.logger.error(ex.getMessage(), ex);
        return new ResponseEntity<ApiErrors>(new ApiErrors(ex), ex.getStatus());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrors> handleResponseStatusException(ResponseStatusException ex) {
        this.logger.error(ex.getMessage(), ex);
        return new ResponseEntity<ApiErrors>(new ApiErrors(ex), ex.getStatus());
    }
}

