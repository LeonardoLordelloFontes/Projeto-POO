package com.grupoxx.smarthouse.exception;

public class HouseAddressAlreadyExists extends RuntimeException {
    public HouseAddressAlreadyExists() {
        super();
    }

    public HouseAddressAlreadyExists(String message) {
        super(message);
    }
}
