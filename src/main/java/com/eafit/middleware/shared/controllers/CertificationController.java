package com.eafit.middleware.shared.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eafit.middleware.shared.dtos.request.RequestCertificationDto;
import com.eafit.middleware.shared.dtos.response.RequirementRequestDto;
import com.eafit.middleware.shared.services.CertificationService;

@RestController
@RequestMapping("/certifications")
public class CertificationController {
    @Autowired
    private CertificationService learningService;

    @GetMapping("/{userId}")
    public List<RequirementRequestDto> getAllCertificationRequests(@PathVariable String userId) {
        return learningService.getAllRequests(userId);
    }   

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void uploadCertificationRequest(@ModelAttribute RequestCertificationDto certification) {
        learningService.uploadCertification(certification);
        return;
    }   

    @PutMapping("/update")
    public void updateStatus(@RequestBody RequirementRequestDto certification) {
        learningService.updateCertification(certification);
        return;
    }
}

