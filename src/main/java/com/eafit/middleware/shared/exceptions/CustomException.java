package com.eafit.middleware.shared.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

public abstract class CustomException extends RuntimeException {
    protected HttpStatusCode statusCode;
    protected String message;

    public Response getResponse() {
        return new Response(message, statusCode.value());
    }

    public static class Response {
        private String error;
        private int status;
        private LocalDateTime timestamp;

        public Response(String message, int status) {
            this.status = status;
            this.error = message;
            this.timestamp = LocalDateTime.now();
        }
    }
}
