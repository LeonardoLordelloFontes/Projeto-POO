package com.grupoxx.energysupplier.exception;

public class EnergySupplierAlreadyExists extends RuntimeException {
    public EnergySupplierAlreadyExists() {
        super();
    }

    public EnergySupplierAlreadyExists(String message) {
        super(message);
    }
}
