package com.eafit.middleware.shared.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserAuthenticated(Integer id, String username, String rol,
        @JsonProperty("change_password") Boolean changePassword, String token) {

}
