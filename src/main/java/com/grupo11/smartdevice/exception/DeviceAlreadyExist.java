package com.grupo11.smartdevice.exception;

/**
 * Exceção RuntimeException particular que ocorre quando queremos criar novos dispositivos
 */
public class DeviceAlreadyExist extends RuntimeException {
    public DeviceAlreadyExist(){super();}

    /**
     * Exceção se os dispositivos já existirem
     * @param message é a mensagem que o utilizador lê quando os dispositivos já existem
     */
    public DeviceAlreadyExist(String message){super(message);}
}
