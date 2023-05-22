package com.eafit.middleware.shared.decorators.Requirement;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.dtos.request.Requirement;

@Component
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
        return requirement.status.equalsIgnoreCase("lock");
    }
    
}
