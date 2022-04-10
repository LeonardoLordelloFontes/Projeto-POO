package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;

import java.util.*;

public class SmartHouse {
    private Owner owner;
    private String address;
    private Map<String, SmartDevice> smartDevices;
    private Map<String, List<String>> rooms;

    public SmartHouse() {
        this.owner = new Owner();
        this.address = "DEFAULT ADDRESS";
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
        this.owner = smartHouse.getOwner();
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
    //Não podes aceder ao estado diretamente tens que usar get e set...
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
        this.smartDevices = smartDevices;
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

    public SmartDevice getSmartDevice(String factoryCode) {
        return smartDevices.get(factoryCode);
    }

    public Map<String, SmartDevice> getSmartDevices() {
        return smartDevices;
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





     }

    /*

    *public double custoCasa(){
         double custo = 0;
         for (List<String> ls : this.rooms.values()) {
             for (String s : ls){
                 SmartDevice sd = this.devices.get(s);
                 if (sd.getState() == SmartDevice.State.ON){
                     custo += sd.EnergeticConsumptionPerDay();
                 }
         }
         return custo;
    *
    *
    *
    *
    * */


}
