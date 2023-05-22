package com.eafit.middleware.shared.dtos.request;


import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Seniority {
    public String id;
    public String name;
    public String description;
    public List<String> priorTo;
    public List<Requirement> requirements;
    public String status;
    public int points;

    public Seniority() {}
}

