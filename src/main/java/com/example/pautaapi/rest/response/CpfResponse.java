package com.example.pautaapi.rest.response;

import java.io.Serializable;

public class CpfResponse implements Serializable {
    private String status;

    public CpfResponse() {

    }
    
    public CpfResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
