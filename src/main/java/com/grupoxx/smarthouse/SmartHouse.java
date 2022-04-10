package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;

import java.util.*;

public class SmartHouse {
    private Owner owner;
    private String address;
    private Map<String, SmartDevice> devices;
    private Map<String, List<String>> rooms;

    public SmartHouse() {
        this.owner = new Owner();
        this.address = "DEFAULT ADDRESS";
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
    }

    public SmartHouse(Owner owner, String address, List<String> rooms) {
        this.owner = owner;
        this.address = address;
        this.devices = new HashMap<>();
        this.rooms = new HashMap<>();
        rooms.forEach(room -> this.rooms.put(room, new ArrayList<>()));
    }

    public SmartHouse(SmartHouse smartHouse) {
        this.owner = smartHouse.getOwner();
        this.address = smartHouse.getAddress();
        this.devices = smartHouse.getDevices();
        this.rooms = smartHouse.getRooms();
    }

    public void addSmartDeviceToHouse(SmartDevice smartDevice) {
        devices.put(smartDevice.getFactoryCode(), smartDevice);
    }

    public void addSmartDeviceToRoom(String smartDevice, String room) {
        rooms.get(room).add(smartDevice);
    }

    public void addRoam(String roam) {
        rooms.put(roam, new ArrayList<>());
    }

    public void setSmartDeviceOn(String smartDevice) {
        devices.get(smartDevice).setState(SmartDevice.State.ON);
    }

    public void setSmartDeviceOff(String smartDevice) {
        devices.get(smartDevice).setState(SmartDevice.State.OFF);
    }

    public void setRoomSmartDevicesOn(String room) {
        rooms.get(room).forEach(device -> devices.get(device).setState(SmartDevice.State.ON));
    }

    public void setRoomSmartDevicesOff(String room) {
        rooms.get(room).forEach(device -> devices.get(device).setState(SmartDevice.State.OFF));
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDevices(Map<String, SmartDevice> devices) {
        this.devices = devices;
    }

    public void setRooms(Map<String, List<String>> rooms) {
        this.rooms = rooms;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public SmartDevice getDevice(String smartDevice) {
        return devices.get(smartDevice);
    }

    public Map<String, SmartDevice> getDevices() {
        return devices;
    }

    public Map<String, List<String>> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHouse smartHouse = (SmartHouse) o;
        return  smartHouse.getOwner().equals(this.owner) && smartHouse.getAddress().equals(this.address) &&
                smartHouse.getDevices().equals(this.devices) && smartHouse.getRooms().equals(this.rooms);
    }

    @Override
    public String toString() {
        // TODO
        StringBuilder sb = new StringBuilder();
        return "";
    }

    public Map<String, List<String>> getRooms() {
        return rooms;
    }

    @Override
    public SmartHouse clone() {
        return new SmartHouse(this);
    }
}
