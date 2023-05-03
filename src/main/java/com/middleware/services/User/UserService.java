package com.middleware.services.User;

import javax.inject.Inject;

import com.google.inject.Singleton;
import com.middleware.decorator.Seniorities.SenioritiesDecorator;
import com.middleware.dto.SeniorityComponent;
import com.middleware.dto.UserDto;
import com.middleware.dto.UserEntity;
import com.middleware.response.MiddlewareResponse;

import io.micronaut.context.LocalizedMessageSource;
import io.micronaut.context.annotation.Requires;


@Singleton
@Requires(env = "scope", value = "prod")
public class UserService implements UserServiceImpl {

    @Override
    public UserDto getUser(UserEntity user) {
        return null;
    }

}
