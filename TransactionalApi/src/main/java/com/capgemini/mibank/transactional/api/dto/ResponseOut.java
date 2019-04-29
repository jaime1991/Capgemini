package com.capgemini.mibank.transactional.api.dto;

/**
 * @author Jaime Cadena
 */
public class ResponseOut {
    private Status status;
    private Object response;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
    
    
}
