package com.example.halisahaapplication.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timeStampt;

    public ErrorResponse(String message, int status, LocalDateTime timeStampt) {
        this.message = message;
        this.status = status;
        this.timeStampt = timeStampt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimeStampt() {
        return timeStampt;
    }

    public void setTimeStampt(LocalDateTime timeStampt) {
        this.timeStampt = timeStampt;
    }
}
