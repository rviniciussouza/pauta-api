package com.example.pautaapi.rest.response;

public class CpfResponse {
    private String status;

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
