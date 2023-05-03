package com.middleware.controller;

import java.security.Principal;

import com.middleware.dto.FileRequestModel;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;

@Controller
@Secured({ "user" })
public class FileController {
    
    @Post(consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.APPLICATION_JSON)
    public Object uploadRequirement(Principal principal, FileRequestModel model) {

        return null;
    }
}
