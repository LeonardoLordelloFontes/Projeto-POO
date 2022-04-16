package com.grupoxx.smarthouse.exception;

public class HouseNotFound extends RuntimeException {
    public HouseNotFound() {
        super();
    }

    public HouseNotFound(String message) {
        super(message);
    }
}
