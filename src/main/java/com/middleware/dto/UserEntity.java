package com.middleware.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserEntity {
    @JsonProperty("user_id")
    public String userId;
    public String username;
    public String role;
    public String password;
    public String dni;
    public boolean changePassword;

    public UserEntity() {
        
    }

    public UserEntity(String dni, String password) {
        this.dni = dni;
        this.password = password;
    }

    public boolean equals(UserEntity user) {
        return this.dni.equals(user.dni) && this.password.equals(user.password);
    }
}
