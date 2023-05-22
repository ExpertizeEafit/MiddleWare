package com.eafit.middleware.shared.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRegisteredDto {
    public NewUserDto user;
    public boolean created;

    public UserRegisteredDto(NewUserDto newUser) {
        this.user = newUser;
        this.created = true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ( other == null || getClass() != other.getClass()) {
            return false;
        }

        UserRegisteredDto otherUser = (UserRegisteredDto) other;
        return otherUser.user.dni.equals(this.user.dni);
    }
}
