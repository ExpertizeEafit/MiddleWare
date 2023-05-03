package com.middleware.services.Learning;

import com.google.inject.Singleton;
import com.middleware.dto.SeniorityComponent;
import com.middleware.response.MiddlewareResponse;

@Singleton
public interface LearningServiceImpl {
    public MiddlewareResponse getLearningPath(); 
}
