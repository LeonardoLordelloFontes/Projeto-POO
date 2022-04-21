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

    public Invoicer (Invoicer i){
        this.owner = i.getOwner();
        this.energySupplier = i.getEnergySupplier();
        this.totalCost = i.getTotalCost();
        this.houseAddress = i.getHouseAddress();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // TODO
        return sb.toString();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Invoicer clone(){

        return new Invoicer(this);
    }

    public Owner getOwner() {
        return this.owner.clone();
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getEnergySupplier() {
        return this.energySupplier;
    }

    public void setEnergySupplier(String energySupplier) {
        this.energySupplier = energySupplier;
    }

    public String getHouseAddress() {
        return this.houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
