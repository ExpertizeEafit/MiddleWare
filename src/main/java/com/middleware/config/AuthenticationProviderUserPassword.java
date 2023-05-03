package com.middleware.config;


import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.reactivestreams.Publisher;

import com.middleware.dto.UserDto;
import com.middleware.dto.UserEntity;
import com.middleware.services.User.UserServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Singleton 
public class AuthenticationProviderUserPassword implements AuthenticationProvider { 

    @Inject
    public UserServiceImpl userService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
                                     
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        UserEntity user = new UserEntity(username, password);
        UserDto loggedUser = userService.getUser(user);

        return Flux.create(emitter -> {
            if (Objects.nonNull(loggedUser)) {
                emitter.next(AuthenticationResponse.success(loggedUser.username, List.of(loggedUser.role), 
                Map.of("user", loggedUser)));
                emitter.complete();
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}