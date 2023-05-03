package com.middleware.services.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;
import com.middleware.config.SystemConfiguration;
import com.middleware.decorator.Seniorities.SenioritiesDecorator;
import com.middleware.dto.UserEntity;
import com.middleware.dto.UserDto;

import io.micronaut.context.annotation.Requires;
import jakarta.inject.Inject;

@Singleton
@Requires(property = "scope", value = "dev")
public class UserServiceMock implements UserServiceImpl {

    private final String USERS_FILE = "users.json";

    @Inject
    private SenioritiesDecorator senioritiesDecorator;

    @Inject
    public UserServiceMock() {
    }

    @Override
    public UserDto getUser(UserEntity user) {
        return SystemConfiguration.loadJsonToList(USERS_FILE,
                UserEntity.class)
                .stream()
                .filter(u -> user.equals(u))
                .map(UserDto::new)
                .findFirst()
                .orElse(null);
    }

}
