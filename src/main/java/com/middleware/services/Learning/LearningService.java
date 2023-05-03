package com.middleware.services.Learning;

import javax.inject.Inject;

import com.google.inject.Singleton;
import com.middleware.decorator.Seniorities.SenioritiesDecorator;
import com.middleware.dto.SeniorityComponent;
import com.middleware.response.MiddlewareResponse;

import io.micronaut.context.LocalizedMessageSource;
import io.micronaut.context.annotation.Requires;


@Singleton
@Requires(env = "scope", value = "prod")
public class LearningService implements LearningServiceImpl {

    @Inject
    private SenioritiesDecorator senioritiesDecorator;

    @Inject
    private LocalizedMessageSource messageSource;

    public MiddlewareResponse getLearningPath() {
        //return messageSource.getMessage("not-found-elements").orElse("Hola");
        return null;
    }
}
