package com.middleware.decorator.Requirement;

import java.util.stream.Stream;

import com.google.inject.Singleton;
import com.middleware.dto.Requirement;

@Singleton
public interface RequirementControlsGenerator {
    public Stream<Requirement> generateControls(Requirement requirement);

    public boolean shouldGenerate(Requirement requirement);
}
