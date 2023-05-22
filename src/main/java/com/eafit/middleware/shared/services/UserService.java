package com.eafit.middleware.shared.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.client.ApiClient;
import com.eafit.middleware.shared.dtos.NewPasswordDto;

@Component
public class UserService {
    @Autowired
    private ApiClient apiClient;

    public boolean changePassword(NewPasswordDto newPassword) {
        apiClient.changePassword(newPassword);
        return true;
    }
}
