package com.eafit.middleware.shared.dtos.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Requirement {
    public String id;
    public String name;
    public String description;
    public String status;
    public int points;
    public List<Control> controls;

    public Requirement() {
        this.controls = new ArrayList<>();
    }

    public static class Control {
        public String name;
        public String url;
    }
}

