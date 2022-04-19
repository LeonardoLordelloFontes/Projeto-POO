package com.grupoxx.energysupplier.exception;

public class EnergySupplierNotFound extends RuntimeException {
    public EnergySupplierNotFound() {
        super();
    }

    public EnergySupplierNotFound(String message) {
        super(message);
    }
}
