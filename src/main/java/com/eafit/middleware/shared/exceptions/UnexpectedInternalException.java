package com.eafit.middleware.shared.exceptions;

import org.springframework.http.HttpStatus;

public class UnexpectedInternalException extends CustomException {

    public UnexpectedInternalException() {
        this("An unexpected error has occurred. Please contact your server administrator.");
    }

    public UnexpectedInternalException(String message) {
        this.message = message;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
