package com.eafit.middleware.shared.decorators.Seniorities;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.dtos.response.SeniorityComponent;
import com.eafit.middleware.shared.dtos.response.SeniorityComponent.Control;

@Component
public class CompletedStatusDecorator implements SeniorityDecorator {
    @Override
    public boolean shouldDecorate(SeniorityComponent seniority) {
        return seniority.requirements != null && seniority.requirements.stream().allMatch(
                requirement -> requirement.status.equals("completed")
        );
    }

    @Override
    public Stream<SeniorityComponent> decorate(SeniorityComponent seniority) {
        seniority.status = "completed";
        seniority.control = new Control("Request Promotion", String.format("%d", seniority.id));

        return Stream.of(seniority);
    }
}
