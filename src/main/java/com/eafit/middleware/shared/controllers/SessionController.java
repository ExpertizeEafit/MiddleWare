package com.eafit.middleware.shared.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eafit.middleware.shared.dtos.NewUserDto;
import com.eafit.middleware.shared.dtos.UserAuthenticated;
import com.eafit.middleware.shared.dtos.UserCredentials;
import com.eafit.middleware.shared.dtos.UserRegisteredDto;
import com.eafit.middleware.shared.services.AuthenticationService;

@RestController
public class SessionController {

    @Autowired
    private AuthenticationService authenticationService;
    

    @PostMapping("/login")
    public UserAuthenticated login(@RequestBody UserCredentials userCredentials) {
        return authenticationService.login(userCredentials);
    }

    @PostMapping("/register")
    @Secured({"admin"})
    public List<UserRegisteredDto> register(@RequestBody List<NewUserDto> users) {
        return authenticationService.register(users);
    }
}
