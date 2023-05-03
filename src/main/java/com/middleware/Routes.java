package com.middleware;

import java.security.Principal;

import com.google.inject.Singleton;
import com.middleware.controller.LearningController;

import io.micronaut.context.ExecutionHandleLocator;
import io.micronaut.web.router.DefaultRouteBuilder;
import jakarta.inject.Inject;

@Singleton
public class Routes extends DefaultRouteBuilder {

    public Routes(ExecutionHandleLocator executionHandleLocator,
            UriNamingStrategy uriNamingStrategy) {
        super(executionHandleLocator, uriNamingStrategy);
    }

    @Inject
    void learningRoutes(LearningController learningController) {
        GET("/learning/", learningController, "index", Principal.class);
    }
    
}
