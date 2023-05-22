package com.eafit.middleware.shared.dtos.response;


import java.util.ArrayList;
import java.util.List;

import com.eafit.middleware.shared.dtos.request.Seniority;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class SeniorityComponent extends Seniority {

    public Style style;
    public String type;

    public SeniorityComponent() {
        super();
    }

    public SeniorityComponent(Seniority seniority) {
        super(seniority.id, seniority.name, seniority.description, seniority.priorTo, seniority.requirements,
                seniority.status, seniority.points);
    }

    public static class Style {
        public List<String> classes;
        public String icon;

        public Style() {
            this.classes =new ArrayList<>();
        }
    }
}

