package com.example.pautaapi.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

public class ApiErrors {
    
    private List<String> erros;

    public ApiErrors(BindingResult bindingResult) {
        this.erros = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            this.erros.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
    }
    
    public ApiErrors(BussinesException bussinesException) {
        this.erros = Arrays.asList(bussinesException.getMessage());
    }
    
    public ApiErrors(ResponseStatusException ex) {
        this.erros = Arrays.asList(ex.getReason());
    }

    public List<String> getErrors() {
        return this.erros;
    }
}