package com.eafit.middleware.shared.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        this.message = message;
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }
}
