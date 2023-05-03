package com.middleware.decorator.Requirement;

import java.util.stream.Stream;

import com.middleware.dto.Requirement;

import io.micronaut.context.annotation.Prototype;

@Prototype
public class UnlockRequirementControlsGenerator implements RequirementControlsGenerator {

    @Override
    public Stream<Requirement> generateControls(Requirement requirement) {
        Requirement.Control control = new Requirement.Control();
        control.name = "Request certification";
        control.url = "/certificates";

        requirement.controls.add(control);

        return Stream.of(requirement);
    }

    @Override
    public boolean shouldGenerate(Requirement requirement) {
        return requirement.status.equals("unlock");
    }
    
}
