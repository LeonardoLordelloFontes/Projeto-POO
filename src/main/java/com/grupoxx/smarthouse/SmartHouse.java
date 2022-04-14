package com.grupoxx.smarthouse;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;

import javax.script.ScriptException;
import java.util.*;
import java.util.stream.Collectors;

public class SmartHouse {
    private Owner owner;
    private String address;
    private Map<String, SmartDeviceRepository> smartDevices; // Room -> Repositório de Smart Devices
    private String energySupplier;

    public SmartHouse() {
        this.owner = new Owner();
        this.address = "DEFAULT ADDRESS";
        this.smartDevices = new HashMap<>();
    }
    public SmartHouse(String address) {
        this.owner = new Owner();
        this.address = address;
        this.smartDevices = new HashMap<>();
    }

    public SmartHouse(SmartHouse smartHouse) {
        this.owner = smartHouse.getOwner().clone();
        this.address = smartHouse.getAddress();
        // TODO
        // this.smartDevices;
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


    public String getSupplier(){
        return this.energySupplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHouse smartHouse = (SmartHouse) o;
        return  smartHouse.getOwner().equals(this.owner) && smartHouse.getAddress().equals(this.address) &&
                smartHouse.getSmartDevices().equals(this.smartDevices) && smartHouse.getRooms().equals(this.rooms);
    }

    @Override
    public String toString() {
        // TODO
        StringBuilder sb = new StringBuilder();
        return "";
    }

    @Override
    public SmartHouse clone() {
        return new SmartHouse(this);
    }

    public double ElectricityMeter() {
        double cost = 0;
        for (List<String> ls : this.rooms.values()) {
            for (String s : ls) {
                SmartDevice sd = this.smartDevices.get(s);
                if (sd.getState() == SmartDevice.State.ON) {
                    cost += sd.EnergeticConsumptionPerDay();
                }
            }
        }
        return cost;
    }



}
