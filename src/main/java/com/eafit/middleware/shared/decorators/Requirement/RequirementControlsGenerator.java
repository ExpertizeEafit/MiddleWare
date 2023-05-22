package com.eafit.middleware.shared.decorators.Requirement;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.dtos.request.Requirement;


@Component
public interface RequirementControlsGenerator {
    public Stream<Requirement> generateControls(Requirement requirement);

    public boolean shouldGenerate(Requirement requirement);
}
