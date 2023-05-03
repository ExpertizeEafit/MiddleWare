package com.middleware.dto;

public class UserDto {
    public String username;
    public String role;
    public String userId;
    public boolean changePassword;


    public UserDto(UserEntity user) {
        this.username = user.username;
        this.role = user.role;
        this.userId = user.userId;
        this.changePassword = user.changePassword;
    }
}
