package com.grupoxx.smarthouse.exception;

public class DuplicateHouseAddress extends RuntimeException {
    public DuplicateHouseAddress() {
        super();
    }

    public DuplicateHouseAddress(String message) {
        super(message);
    }
}
