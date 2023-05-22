package com.eafit.middleware.shared.decorators.Seniorities;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.eafit.middleware.shared.dtos.response.SeniorityComponent;
import com.eafit.middleware.shared.dtos.response.SeniorityComponent.Style;

@Component
public class ComplexSeniorityDecorator implements SeniorityDecorator {
    public final static String COMPLEX_CLASSES = "seniority-complex state-%s";
    public final static String ICON = "icon-%s";

    @Override
    public boolean shouldDecorate(SeniorityComponent seniority) {
        return seniority.priorTo.size() >= 2;
    }

    @Override
    public Stream<SeniorityComponent> decorate(SeniorityComponent seniority) {
        Style style = new Style();
        seniority.status = getSeniorityStatus(seniority);
        style.classes.add(String.format(COMPLEX_CLASSES, seniority.status));
        style.icon = String.format(ICON, seniority.id);
        seniority.style = style;
        seniority.type = "complex";

        return Stream.of(seniority);
    }
}
