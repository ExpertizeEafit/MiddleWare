package com.middleware.decorator.Seniorities;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import com.google.inject.Singleton;
import com.middleware.decorator.Requirement.RequirementsDecorator;
import com.middleware.dto.Seniority;
import com.middleware.dto.SeniorityComponent;

import jakarta.inject.Inject;

@Singleton
public class SenioritiesDecorator {

    private final List<SeniorityDecorator> decorators;

    @Inject
    SenioritiesSortingService senioritiesSortingService;

    @Inject
    RequirementsDecorator requirementsDecorator;

    @Inject
    public SenioritiesDecorator(SimpleSeniorityDecorator simpleSeniorityDecorator,
            ComplexSeniorityDecorator complexSeniorityDecorator) {
        decorators = List.of(simpleSeniorityDecorator, complexSeniorityDecorator);
    }

    public List<SeniorityComponent> decorate(Map<String, Seniority> seniorities,
            Map<String, Seniority> currentSeniorities) {

        mergeSeniorities(seniorities, currentSeniorities);

        List<Seniority> senioritiesToReturn = senioritiesSortingService.sortMergedSeniorities(seniorities);

        return senioritiesToReturn.stream().map(SeniorityComponent::new)
                .map(this::decorateSeniorityValue)
                .flatMap(seniority -> seniority)
                .map(seniority -> Objects.nonNull(seniority.requirements) ? decorateRequirements(seniority)
                        : Stream.of(seniority))
                .flatMap(seniority -> seniority)
                .toList();
    }

    private Stream<SeniorityComponent> decorateSeniorityValue(SeniorityComponent originalSeniority) {
        Stream<SeniorityComponent> seniority = Stream.of(originalSeniority);

        for (SeniorityDecorator decorator : decorators) {
            seniority = seniority.flatMap(decoratedSeniority -> decorator.shouldDecorate(decoratedSeniority)
                    ? decorator.decorate(decoratedSeniority)
                    : Stream.of(decoratedSeniority));
        }

        return seniority;
    }

    private Stream<SeniorityComponent> decorateRequirements(SeniorityComponent originalSeniority) {
        originalSeniority.requirements = requirementsDecorator.decorate(originalSeniority.requirements);

        return Stream.of(originalSeniority);
    }

    private void mergeSeniorities(Map<String, Seniority> seniorities, Map<String, Seniority> currentSeniorities) {
        currentSeniorities.forEach(
                (seniorityId, body) -> {
                    Seniority seniority = seniorities.get(seniorityId);
                    seniority.requirements = body.requirements;
                });
    }
}
