package com.ikub.healthcare.domain.exception;

import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends RuntimeException  {

    private String errorMessage;
    public BadRequestException (String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
