package com.middleware.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.micronaut.core.annotation.Introspected;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class SeniorityComponent extends Seniority {

    public Style style;
    public String type;

    public SeniorityComponent() {
        super();
    }

    public SeniorityComponent(Seniority seniority) {
        super(seniority.id, seniority.name, seniority.description, seniority.priorTo, seniority.requirements,
                seniority.status);
    }

    public static class Style {
        public List<String> classes;
        public String icon;

        public Style() {
            this.classes =new ArrayList<>();
        }
    }
}
