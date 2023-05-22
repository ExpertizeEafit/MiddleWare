package com.eafit.middleware.shared.dtos.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LearningPathResponse {

    public List<SeniorityComponent> learningPath;
    public String message;
    public String status;
    public Score scoreInfo;

    public LearningPathResponse() {
    }

    public LearningPathResponse(List<SeniorityComponent> learningPath, String message, String status) {
        this.learningPath = learningPath;
        this.message = message;
        this.status = status;
    }

    public static class Score {
        public int points;
        public int position;
        public int globalPosition;
        public int progress;

        public Score() {}
    }
}

