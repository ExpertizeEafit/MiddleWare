package com.eafit.middleware.shared.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eafit.middleware.shared.dtos.NewPasswordDto;
import com.eafit.middleware.shared.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService; 

    @PostMapping("/change_password")
    public void changePassword(@RequestBody NewPasswordDto newPassword) {
        userService.changePassword(newPassword);
    }
}