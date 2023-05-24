package com.eafit.middleware.shared.exceptions;

import org.springframework.http.HttpStatus;

public class RegisterUsersException extends CustomException {
    public RegisterUsersException(String message) {
        this.message = message;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
