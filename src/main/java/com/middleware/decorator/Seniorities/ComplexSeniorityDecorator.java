package com.middleware.decorator.Seniorities;

import java.util.stream.Stream;

import com.middleware.dto.SeniorityComponent;
import com.middleware.dto.SeniorityComponent.Style;

import io.micronaut.context.annotation.Prototype;

@Prototype
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
