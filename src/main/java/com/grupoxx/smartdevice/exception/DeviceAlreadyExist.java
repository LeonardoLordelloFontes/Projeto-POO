package com.grupoxx.smartdevice.exception;

public class DeviceAlreadyExist extends RuntimeException {
    public DeviceAlreadyExist(){super();}

    public DeviceAlreadyExist(String message){super(message);}
}
