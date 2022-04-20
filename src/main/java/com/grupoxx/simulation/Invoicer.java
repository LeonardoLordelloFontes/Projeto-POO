package com.grupoxx.simulation;

import com.grupoxx.smarthouse.Owner;

import java.io.Serializable;

public class Invoicer implements Serializable {
    private Owner owner;
    private String energySupplier;
    double totalCost;
    String houseAddress;

    public Invoicer(Owner owner, String energySupplier, double totalCost, String houseAddress) {
        this.owner = owner;
        this.energySupplier = energySupplier;
        this.totalCost = totalCost;
        this.houseAddress = houseAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // TODO
        return sb.toString();
    }
}
