package com.grupo11.smartdevice.exception;

/**
 * Exceção RuntimeException particular que ocorre quando queremos remover ou atualizar dispositivos
 */
public class DeviceNotFound extends RuntimeException{

    public DeviceNotFound(){super();}

    /**
     * Exceção se os dispositivos não existirem
     * @param message é a mensagem que o utilizador lê quando os dispositivos não existem
     */
    public DeviceNotFound(String message){super(message);}
}
