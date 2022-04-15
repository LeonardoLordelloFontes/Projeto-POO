package com.grupoxx.smarthouse.exception;

public class NoneHouseAvailable extends RuntimeException {
    public NoneHouseAvailable() {
        super();
    }

    public NoneHouseAvailable(String message) {
        super(message);
    }
}
