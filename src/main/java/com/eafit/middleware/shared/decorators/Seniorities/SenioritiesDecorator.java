package com.eafit.middleware.shared.decorators.Seniorities;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.decorators.Requirement.RequirementsDecorator;
import com.eafit.middleware.shared.dtos.request.Seniority;
import com.eafit.middleware.shared.dtos.response.SeniorityComponent;

@Component
public class SenioritiesDecorator {

    private final List<SeniorityDecorator> decorators;

    @Autowired
    SenioritiesSortingService senioritiesSortingService;

    @Autowired
    RequirementsDecorator requirementsDecorator;

    @Autowired
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
        if(Objects.isNull(currentSeniorities) || Objects.isNull(seniorities)) {
            return;
        }

        currentSeniorities.forEach(
                (seniorityId, body) -> {
                    Seniority seniority = seniorities.get(seniorityId);
                    seniority.requirements = body.requirements;
                });
    }
}
