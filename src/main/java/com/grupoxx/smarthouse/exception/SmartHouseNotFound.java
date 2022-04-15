package com.grupoxx.smarthouse.exception;

public class SmartHouseNotFound extends RuntimeException {
    public SmartHouseNotFound() {
        super();
    }

    public SmartHouseNotFound(String message) {
        super(message);
    }
}
