package com.eafit.middleware.shared.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewUserDto {
    public String dni;
    public String name;
    public String lastname;
    public String seniorityId;
    public String areaId;
}
