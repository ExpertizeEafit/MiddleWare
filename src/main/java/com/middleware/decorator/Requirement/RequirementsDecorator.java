package com.middleware.decorator.Requirement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.middleware.dto.Requirement;

import io.micronaut.context.annotation.Prototype;

@Prototype
public class RequirementsDecorator {

    public List<RequirementControlsGenerator> generators;

    public RequirementsDecorator() {
    }

    @Inject
    public RequirementsDecorator(PendingRequirementControlsGenerator completedRequirementControlsGenerator,
            UnlockRequirementControlsGenerator unlockRequirementControlsGenerator) {
        generators = List.of(completedRequirementControlsGenerator, unlockRequirementControlsGenerator);
    }

    public List<Requirement> decorate(List<Requirement> requirements) {
        return requirements.stream().map(this::decorateRequirement).flatMap(
                decoratedRequirement -> decoratedRequirement).collect(Collectors.toList());
    }

    private Stream<Requirement> decorateRequirement(Requirement requirement) {
        Stream<Requirement> decoratedRequirement = Stream.of(requirement);

        for (RequirementControlsGenerator generator : generators) {
            decoratedRequirement = decoratedRequirement.flatMap(decoratedReq -> generator.shouldGenerate(decoratedReq)
                    ? generator.generateControls(decoratedReq)
                    : Stream.of(decoratedReq));
        }

        return decoratedRequirement;
    }
}
