package com.grupoxx.EnergySupplier.exception;

public class EnergySupplierNotFound extends RuntimeException {
    public EnergySupplierNotFound() {
        super();
    }

    public EnergySupplierNotFound(String message) {
        super(message);
    }
}
