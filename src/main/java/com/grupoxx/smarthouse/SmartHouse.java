package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;

import java.io.Serializable;
import java.util.*;

public class SmartHouse implements Serializable {
    private Owner owner;
    private String address;
    private String energySupplier;
    private final Map<String, SmartDeviceRepository> smartDevices; // Room -> Repositório de Smart Devices

    public SmartHouse() {
        this.owner = new Owner();
        this.address = "DEFAULT ADDRESS";
        this.energySupplier = null;
        this.smartDevices = new HashMap<>();
    }
    public SmartHouse(String address) {
        this.owner = null;
        this.address = address;
        this.energySupplier = null;
        this.smartDevices = new HashMap<>();
    }

    public SmartHouse(SmartHouse smartHouse) {
        this.owner = smartHouse.getOwner();
        this.address = smartHouse.getAddress();
        this.energySupplier = smartHouse.getEnergySupplier();
        this.smartDevices = smartHouse.getSmartDevices();
    }

    public void setOwner(Owner owner) {
        this.owner = owner.clone();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEnergySupplier(String energySupplier) {
        this.energySupplier = energySupplier;
    }

    public Owner getOwner() {
        return owner.clone();
    }

    public String getAddress() {
        return address;
    }

    public String getEnergySupplier() {
        return energySupplier;
    }

    public Map<String, SmartDeviceRepository> getSmartDevices() {
        return smartDevices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHouse smartHouse = (SmartHouse) o;
        return  smartHouse.getOwner().equals(this.owner) &&
                smartHouse.getAddress().equals(this.address) &&
                smartHouse.getEnergySupplier().equals(this.energySupplier) &&
                smartHouse.getSmartDevices().equals(this.smartDevices);
    }

    @Override
    public String toString() {
        // TODO
        StringBuilder sb = new StringBuilder();
        sb.append(this.address).append("\n");
        smartDevices.forEach((key, value) -> sb.append("\t").append(key).append("\n"));
        return sb.toString();
    }

    @Override
    public SmartHouse clone() {
        return new SmartHouse(this);
    }
}
