package com.grupo11.energysupplier.exception;

public class EnergySupplierAlreadyExists extends RuntimeException {
    public EnergySupplierAlreadyExists() {
        super();
    }

    public EnergySupplierAlreadyExists(String message) {
        super(message);
    }
}
