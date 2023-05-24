package com.eafit.middleware.shared.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.client.ApiClient;
import com.eafit.middleware.shared.dtos.Status;
import com.eafit.middleware.shared.dtos.request.RequestCertificationDto;
import com.eafit.middleware.shared.dtos.request.Requirement;
import com.eafit.middleware.shared.dtos.request.Seniority;
import com.eafit.middleware.shared.dtos.response.CertificationResponse;
import com.eafit.middleware.shared.dtos.response.RequirementRequestDto;

@Component
public class CertificationService {
    @Autowired
    private ApiClient apiClient;

    public CertificationResponse getCertificationResponse(String userId) {

        CompletableFuture<List<RequirementRequestDto>> certificationsHistory = CompletableFuture
                .supplyAsync(() -> getAllRequests(userId));
        CompletableFuture<List<Requirement>> availableRequirements = CompletableFuture
                .supplyAsync(() -> getAvailableRequirements(userId));

        CompletableFuture<CertificationResponse> joinedData = CompletableFuture
                .allOf(certificationsHistory, availableRequirements).thenApplyAsync(
                        aVoid -> {
                            CertificationResponse response = new CertificationResponse();
                            response.availableRequirements = availableRequirements.join();
                            response.certificationsHistory = certificationsHistory.join();

                            return response;
                        });

        return joinedData.join();
    }

    public List<Requirement> getAvailableRequirements(String userId) {
        Map<String, Seniority> seniorities = apiClient.getLearningPathByUser(userId);

        List<Requirement> requirements = new ArrayList<>();

        seniorities.forEach((key, value) -> {
            if (Objects.nonNull(value.requirements)) {
                requirements.addAll(value.requirements);
            }
        });

        return requirements.stream().filter(requirement -> Status.LOCK.name().equalsIgnoreCase(requirement.status)).toList();
    }

    public List<RequirementRequestDto> getAllRequests(String userId) {
        return apiClient.getAllRequests(userId);
    }

    public void uploadCertification(RequestCertificationDto certification) {
        String response = apiClient.uploadRequirement(certification);

        if (Objects.isNull(response) || Strings.isEmpty(response)) {
            // TODO: Throw Exception
        }
    }
    
    public void updateCertification(RequirementRequestDto certification) {
        String response = apiClient.updateRequirement(certification);

        if (Objects.isNull(response) || Strings.isEmpty(response)) {
            // TODO: Throw Exception
        }
    }
}
