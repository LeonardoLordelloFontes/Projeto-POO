package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SmartHouseRepository {

    private Map<String, SmartHouse> smartHouses;

    public SmartHouseRepository() {
        this.smartHouses = new HashMap<>();
    }

    public SmartHouse getHouseByAddress(String address) {
        return smartHouses.get(address);
    }

    public boolean addSmartHouse(String address) {
            SmartHouse smartHouse = new SmartHouse(address);
            smartHouses.put(address, smartHouse);
            return true;
    }

    public boolean addRoomToHouse(String address, String room) {
        smartHouses.get(address).addRoom(room);
        return true;
    }

    public boolean removeHouseByAddress(String address) {
            smartHouses.remove(address);
            return true;
    }

    public boolean updateHouseAddress(String oldAddress, String newAddress) {
            smartHouses.get(oldAddress).setAddress(newAddress);
            return true;
    }

    public boolean updateEnergySupplier(String address, String newEnergySupplier) {
        smartHouses.get(address).setEnergySupplier(newEnergySupplier);
        return true;
    }

    public boolean updateOwner(String address, Owner newOwner) {
        smartHouses.get(address).setOwner(newOwner);
        return true;
    }

    public List<SmartHouse> findAllSmartHouses() {
        return new ArrayList<>(smartHouses.values());
    }
}
