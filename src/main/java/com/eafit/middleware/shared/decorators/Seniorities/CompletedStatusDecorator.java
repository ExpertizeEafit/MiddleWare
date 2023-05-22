package com.eafit.middleware.shared.decorators.Seniorities;

import java.util.stream.Stream;

import com.eafit.middleware.shared.dtos.response.SeniorityComponent;

public class CompletedStatusDecorator implements SeniorityDecorator {
    @Override
    public boolean shouldDecorate(SeniorityComponent seniority) {
        return seniority.requirements.stream().allMatch(
                requirement -> requirement.status.equals("completed")
        );
    }

    @Override
    public Stream<SeniorityComponent> decorate(SeniorityComponent seniority) {
        seniority.status = "completed";

        return Stream.of(seniority);
    }
}
