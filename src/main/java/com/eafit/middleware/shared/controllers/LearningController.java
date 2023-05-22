package com.eafit.middleware.shared.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eafit.middleware.shared.dtos.response.LearningPathResponse;
import com.eafit.middleware.shared.services.LearningService;

@RestController
public class LearningController {
    @Autowired
    private LearningService learningService;

    @GetMapping("/learning/{userId}")
    public LearningPathResponse getLearningPath(@PathVariable String userId) {
        return learningService.getLearningPath(userId);
    }   
}
