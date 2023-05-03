package com.middleware.dto;

import java.util.List;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Introspected
public class Seniority {
    public String id;
    public String name;
    public String description;
    public List<String> priorTo;
    public List<Requirement> requirements;
    public String status;

    public Seniority() {}
}
