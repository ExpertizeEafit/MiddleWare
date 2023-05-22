package com.eafit.middleware.shared.services;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.client.ApiClient;
import com.eafit.middleware.shared.dtos.request.RequestCertificationDto;
import com.eafit.middleware.shared.dtos.response.RequirementRequestDto;

@Component
public class CertificationService {
    @Autowired
    private ApiClient apiClient;

    public List<RequirementRequestDto> getAllRequests(String userId) {
        return apiClient.getAllRequests(userId);
    }

    public void uploadCertification(RequestCertificationDto certification) {
        String response = apiClient.uploadRequirement(certification);

        if (Objects.isNull(response) || Strings.isEmpty(response)) {
            //TO DO: Throw Exception
        }
    }

    public void updateCertification(RequirementRequestDto certification) {
        String response = apiClient.updateRequirement(certification);

        if (Objects.isNull(response) || Strings.isEmpty(response)) {
            //TO DO: Throw Exception
        }
    }
}
