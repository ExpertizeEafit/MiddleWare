package com.middleware.config;

import java.util.Collection;
import java.util.Map;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.middleware.dto.UserDto;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerTokenRenderer;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

@Replaces(BearerTokenRenderer.class)
@Singleton
public class CustomBearerTokenRender extends BearerTokenRenderer {
    private final static String BEARER_TOKEN_TYPE = "Bearer";
;
    @Override
    public AccessRefreshToken render(Authentication authentication, Integer expiresIn, String accessToken, @Nullable String refreshToken) {
        
        return new CustomToken(authentication.getName(), authentication.getRoles(), authentication.getAttributes(), expiresIn, accessToken, refreshToken, BEARER_TOKEN_TYPE);
    }

    public static class CustomToken extends BearerAccessRefreshToken {
        public UserDto user;

        public CustomToken(String name, Collection<String> roles, Map<String, Object> attributes, Integer expiresIn, String accessToken,
                String refreshToken, String bearerTokenType) {
                super(null, roles, expiresIn, accessToken, refreshToken, BEARER_TOKEN_TYPE);
                
                user = (UserDto) attributes.get("user");
        }
    }
}