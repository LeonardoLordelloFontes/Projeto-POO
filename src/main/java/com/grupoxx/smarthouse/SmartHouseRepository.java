package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;

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

    public boolean addSmartHouse(String address) {
        try {
            SmartHouse smartHouse = new SmartHouse(address);
            smartHouses.put(address, smartHouse);
            return true;
        } catch (Exception e) {
            // TODO, por exemplo se já existir este endereço
            return false;
        }
    }

    public SmartHouse getHouseByAddress(String address) {
        try {
            return smartHouses.get(address).clone();
        } catch (Exception e) {
            // TODO, por exemplo se não existir a casa
            return null;
        }
    }

    public boolean removeHouseByAddress(String address) {
        try {
            smartHouses.remove(address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addSmartDeviceToHouse(SmartDevice smartDevice, String address, String room) {
        try {
            smartHouses.get(address).addSmartDevice(smartDevice, room);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public boolean addRoomToHouse(String address, String room) {
        try {
            smartHouses.get(address).addRoom(room);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public boolean removeRoomFromHouse(String address, String room) {
        try {
            smartHouses.get(address).getRooms().remove(room);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public boolean removeSmartDeviceFromHouse(String address, String factoryCode) {
        try {
            smartHouses.get(address).getRooms().remove(factoryCode);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public boolean updateHouseAddress(String oldAddress, String newAddress) {
        try {
            smartHouses.get(oldAddress).setAddress(newAddress);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public boolean updateEnergySupplier(String address, String newEnergySupplier) {
        try {
            smartHouses.get(address).setEnergySupplier(newEnergySupplier);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public boolean updateOwner(String address, Owner newOwner) {
        try {
            smartHouses.get(address).setOwner(newOwner);
            return true;
        } catch (Exception e) {
            // TODO
            return false;
        }
    }

    public List<SmartHouse> getAllSmartHouses() {
        try {
            return new ArrayList<>(smartHouses.values());
        } catch (Exception e) {
            // TODO
            return null;
        }
    }
}
