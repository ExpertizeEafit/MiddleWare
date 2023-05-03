package com.middleware.decorator.Requirement;

import java.util.stream.Stream;

import com.google.inject.Singleton;
import com.middleware.dto.Requirement;

import io.micronaut.context.annotation.Prototype;

@Prototype
public class PendingRequirementControlsGenerator implements RequirementControlsGenerator {

    @Override
    public Stream<Requirement> generateControls(Requirement requirement) {
        Requirement.Control control = new Requirement.Control();
        control.name = "View status";
        control.url = "/progress";

        requirement.controls.add(control);

        return Stream.of(requirement);
    }

    @Override
    public boolean shouldGenerate(Requirement requirement) {
        return requirement.status.equals("pending");
    }
    
}
