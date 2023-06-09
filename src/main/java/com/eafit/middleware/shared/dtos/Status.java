package com.eafit.middleware.shared.dtos;

public enum Status {
    COMPLETED("Completed"),
    IN_PROGRESS("Pending"),
    UNLOCKED("Unlocked"),
    LOCKED("Locked");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
