package com.eafit.middleware.shared.dtos.response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequirementRequestDto {
    public int id;
    public String name;
    public String last_update;
    public String status;

    public RequirementRequestDto() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        this.last_update = formatoFecha.format(fechaActual);
    }
}
