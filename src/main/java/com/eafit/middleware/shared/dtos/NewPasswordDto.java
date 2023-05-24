package com.eafit.middleware.shared.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPasswordDto {
    public int id;
    @JsonProperty("old_password")
    public String oldPassword;
    @JsonProperty("new_password")
    public String newPassword;
}
