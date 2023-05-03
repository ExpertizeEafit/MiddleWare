package com.middleware.controller;

import com.middleware.response.MiddlewareResponse;
import com.middleware.services.Learning.LearningServiceImpl;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;

import java.security.Principal;

import javax.inject.Inject;

// @Secured(SecurityRule.IS_AUTHENTICATED)
@Secured({"user"})
@Controller
public class LearningController {
    
    @Inject
    LearningServiceImpl learningService;
    
    @Produces(MediaType.APPLICATION_JSON)
    @Get
    public MiddlewareResponse index(Principal principal) {
        return learningService.getLearningPath();
    }

}
