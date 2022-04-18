package com.grupoxx.EnergySupplier.exception;

public class EnergySupplierAlreadyExists extends RuntimeException {
    public EnergySupplierAlreadyExists() {
        super();
    }

    public EnergySupplierAlreadyExists(String message) {
        super(message);
    }
}
