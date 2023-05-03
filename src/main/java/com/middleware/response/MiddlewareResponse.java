package com.middleware.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.middleware.dto.SeniorityComponent;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MiddlewareResponse {

    public List<SeniorityComponent> learningPath;
    public String message;
    public String status;

    public MiddlewareResponse() {
    }

    public MiddlewareResponse(List<SeniorityComponent> learningPath, String message, String status) {
        this.learningPath = learningPath;
        this.message = message;
        this.status = status;
    }

}
