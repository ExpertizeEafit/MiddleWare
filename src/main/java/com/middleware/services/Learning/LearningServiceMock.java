package com.middleware.services.Learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;
import com.middleware.config.SystemConfiguration;
import com.middleware.decorator.Seniorities.SenioritiesDecorator;
import com.middleware.dto.Seniority;
import com.middleware.response.MiddlewareResponse;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Secondary;
import jakarta.inject.Inject;

@Singleton
@Requires(property = "scope", value = "dev")
public class LearningServiceMock implements LearningServiceImpl {

    private final String LEARNING_PATH_RESULTS = "backend.json";
    private final String CURRENT_LEARNING_PATH_RESULTS = "senior.json";

    @Inject
    private SenioritiesDecorator senioritiesDecorator;

    @Inject
    public LearningServiceMock() {
    }

    @Override
    public MiddlewareResponse getLearningPath() {
        MiddlewareResponse response = new MiddlewareResponse();

        bindLearningPathResults(response);

        return response;
    }

    private void bindLearningPathResults(MiddlewareResponse response) {
        Map<String, Seniority> learningPathList = SystemConfiguration.loadJsonToMap(LEARNING_PATH_RESULTS,
                String.class,
                Seniority.class);

        Map<String, Seniority> currentLearningPath = SystemConfiguration.loadJsonToMap(
                CURRENT_LEARNING_PATH_RESULTS,
                String.class,
                Seniority.class);

        response.learningPath = senioritiesDecorator.decorate(learningPathList, currentLearningPath);
    }

}
