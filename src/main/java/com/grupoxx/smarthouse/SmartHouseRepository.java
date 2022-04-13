package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;

import java.util.HashMap;
import java.util.Map;

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
}
