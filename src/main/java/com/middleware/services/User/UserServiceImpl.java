package com.middleware.services.User;

import com.google.inject.Singleton;
import com.middleware.dto.UserDto;
import com.middleware.dto.UserEntity;

@Singleton
public interface UserServiceImpl {
    public UserDto getUser(UserEntity user);
}
