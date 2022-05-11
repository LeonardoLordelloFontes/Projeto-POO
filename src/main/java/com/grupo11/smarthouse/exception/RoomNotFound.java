package com.grupo11.smarthouse.exception;

public class RoomNotFound extends RuntimeException {
    public RoomNotFound() {
        super();
    }

    public RoomNotFound(String message) {
        super(message);
    }
}
