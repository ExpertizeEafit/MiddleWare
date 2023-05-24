package com.eafit.middleware.shared.dtos.response;

import java.util.List;

import com.eafit.middleware.shared.dtos.request.Requirement;

public class CertificationResponse {
    public List<RequirementRequestDto> certificationsHistory;
    public List<Requirement> availableRequirements;
}
