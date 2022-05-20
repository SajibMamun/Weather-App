package com.example.weatherapp.Events;

public class ErrorEvents {
    private final String errorMessage;

    public ErrorEvents(String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
