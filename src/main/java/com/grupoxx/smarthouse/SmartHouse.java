package com.grupoxx.smarthouse;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.smartdevice.SmartDevice;

import javax.script.ScriptException;
import java.util.*;
import java.util.stream.Collectors;

public class SmartHouse {
    private Owner owner;
    private String address;
    private Map<String, SmartDevice> smartDevices;
    private Map<String, List<String>> rooms;
    private String energySupplier;

    public SmartHouse() {
        this.owner = new Owner();
        this.address = "DEFAULT ADDRESS";
        this.smartDevices = new HashMap<>();
        this.rooms = new HashMap<>();
    }
    public SmartHouse(String address) {
        this.owner = new Owner();
        this.address = address;
        this.smartDevices = new HashMap<>();
        this.rooms = new HashMap<>();
    }


    public SmartHouse(Owner owner, String address, List<String> rooms) {
        this.owner = owner.clone();
        this.address = address;
        this.smartDevices = new HashMap<>();
        this.rooms = new HashMap<>();
        rooms.forEach(this::addRoom);
    }

    public SmartHouse(Owner owner, String address, Map<String, SmartDevice> devices, Map<String, List<String>> rooms) {
        this.owner = owner.clone();
        this.address = address;
        this.smartDevices = devices;
        this.rooms = rooms;
    }

    public SmartHouse(SmartHouse smartHouse) {
        this.owner = smartHouse.getOwner().clone();
        this.address = smartHouse.getAddress();
        this.smartDevices = smartHouse.getSmartDevices();
        this.rooms = smartHouse.getRooms();
    }

    public void addSmartDevice(SmartDevice smartDevice, String room) {
        String factoryCode = smartDevice.getFactoryCode();
        smartDevices.put(factoryCode, smartDevice);
        rooms.get(room).add(factoryCode);
    }

    public void addRoom(String room) {
        rooms.put(room, new ArrayList<>());
    }

    public void setSmartDeviceOn(String smartDevice) {
        smartDevices.get(smartDevice).setState(SmartDevice.State.ON);
    }

    public void setSmartDeviceOff(String smartDevice) {
        smartDevices.get(smartDevice).setState(SmartDevice.State.OFF);
    }

    public void setRoomSmartDevicesOn(String room) {
        rooms.get(room).forEach(device -> smartDevices.get(device).setState(SmartDevice.State.ON));
    }

    public void setRoomSmartDevicesOff(String room) {
        rooms.get(room).forEach(device -> smartDevices.get(device).setState(SmartDevice.State.OFF));
    }

    public void setOwner(Owner owner) {
        this.owner = owner.clone();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSmartDevices(Map<String, SmartDevice> smartDevices) {

        this.smartDevices = smartDevices.entrySet().stream().collect(Collectors.toMap(x->x.getKey(),x->x.getValue().clone()));
    }

    public void setRooms(Map<String, List<String>> rooms) {
        Map<String, List<String> > r = new HashMap<String, List<String>>();
        List <String> rr = new ArrayList<String>();

        for (String s: rooms.keySet()){
            rr = this.rooms.get(s).stream().toList();
            r.put(s,rr);
        }

        this.rooms = r;

    }

    public Owner getOwner() {
        return owner.clone();
    }

    public String getAddress() {
        return address;
    }

    public SmartDevice getSmartDevice(String factoryCode) {
        return this.smartDevices.get(factoryCode);
    }

    public Map<String, SmartDevice> getSmartDevices() {

        Map<String, SmartDevice> sd = new HashMap<String,SmartDevice>();

        sd = this.smartDevices.entrySet().stream().collect(Collectors.toMap( e->e.getKey(),e->e.getValue().clone() ));

        return sd;
    }

    public Map<String, List<String>> getRooms() {

        Map<String, List<String> > r = new HashMap<String, List<String>>();
        List <String> rr = new ArrayList<String>();

        for (String s: this.rooms.keySet()){
            rr = this.rooms.get(s).stream().toList();
            r.put(s,rr);
        }

        return r;
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
