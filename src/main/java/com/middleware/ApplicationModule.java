package com.middleware;

import com.google.inject.AbstractModule;
import com.middleware.config.SystemConfiguration;
import com.middleware.services.Learning.LearningServiceImpl;
import com.middleware.services.Learning.LearningServiceMock;

import io.micronaut.context.annotation.Factory;

@Factory
public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bindBeans();
    }

    private void bindBeans() {
        boolean isProd = "prod".equals(SystemConfiguration.getEnv("scope"));

        if (!isProd) {
            bind(LearningServiceImpl.class).to(LearningServiceMock.class);
        }
    }
}