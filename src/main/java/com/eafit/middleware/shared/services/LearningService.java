package com.eafit.middleware.shared.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eafit.middleware.shared.client.ApiClient;
import com.eafit.middleware.shared.decorators.Seniorities.SenioritiesDecorator;
import com.eafit.middleware.shared.dtos.Status;
import com.eafit.middleware.shared.dtos.request.Seniority;
import com.eafit.middleware.shared.dtos.response.LearningPathResponse;
import com.eafit.middleware.shared.dtos.response.LearningPathResponse.Score;

@Service
public class LearningService {

    @Autowired
    private SenioritiesDecorator senioritiesDecorator;

    @Autowired
    private ApiClient apiClient;

    public LearningPathResponse getLearningPath(String userId) {
        LearningPathResponse response = new LearningPathResponse();

        bindLearningPathResults(userId, response);
        // bindLearningPathScore(response);

        return response;
    }

    private void bindLearningPathResults(String userId, LearningPathResponse response) {
        Map<String, Seniority> learningPathList = apiClient.getLearningPath();

        Map<String, Seniority> currentLearningPath = apiClient.getLearningPathByUser(userId);

        response.learningPath = senioritiesDecorator.decorate(learningPathList, currentLearningPath);
    }

    private void bindLearningPathScore(LearningPathResponse response) {
        // response.scoreInfo = new Score();
        
        // int totalPoints = response.learningPath.stream().mapToInt( seniority -> seniority.points ).sum();
        // int currentPoints = response.learningPath.stream()
        //     .flatMap( seniority -> seniority.requirements )
        //     .filter(requirement -> requirement.status.equalsIgnoreCase(Status.COMPLETED))
        //     .mapToInt( requirement -> seniority.points ).sum();

        // // TODO: Para cada Seniority, sumar los puntos de los requirements que estan completados!

        // response.scoreInfo.points = currentPoints;
        // response.scoreInfo.progress = (int) ((currentPoints / (double) totalPoints) * 100);
    }
}
