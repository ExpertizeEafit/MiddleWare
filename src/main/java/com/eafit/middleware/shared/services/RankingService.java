package com.eafit.middleware.shared.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.client.ApiClient;
import com.eafit.middleware.shared.dtos.response.RankingResponse;

@Component
public class RankingService {
    @Autowired
    private ApiClient apiClient;

    public RankingResponse getRanking(String userId) {
        return apiClient.getRankingByUser(userId);
    }
}
