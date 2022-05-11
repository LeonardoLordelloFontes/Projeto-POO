package com.grupo11.smarthouse.exception;

public class RoomAlreadyExists extends RuntimeException {
    public RoomAlreadyExists() {
        super();
    }

    public RoomAlreadyExists(String message) {
        super(message);
    }
}
