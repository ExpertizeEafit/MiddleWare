package com.middleware.decorator.Seniorities;

import java.util.Objects;
import java.util.stream.Stream;
import com.middleware.dto.SeniorityComponent;

public interface SeniorityDecorator {
    default boolean shouldDecorate(SeniorityComponent seniority) {
        return false;
    }

    default Stream<SeniorityComponent> decorate(SeniorityComponent seniority) {
        return Stream.of(seniority);
    }   

    default String getSeniorityStatus(SeniorityComponent seniority) {
        if(Objects.isNull(seniority.requirements)) {
            return "Locked";
        }

        boolean hasCompleted = seniority.requirements.stream().allMatch(
                requirement -> requirement.status.equals("completed")
        );

        boolean hasStarted = seniority.requirements.stream().anyMatch(
                requirement -> requirement.status.equals("pending") || requirement.status.equals("completed")
        );


        if (hasCompleted) {
            return "Completed";
        } else if (hasStarted) {
            return "In Progress";
        } else {
            return "Locked";
        }
    }
}
