package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.exception.DuplicateHouseAddress;
import com.grupoxx.smarthouse.exception.NoneHouseAvailable;
import com.grupoxx.smarthouse.exception.SmartHouseNotFound;

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
        return smartHouses.getOrDefault(address, null);
    }

    public void addSmartHouse(String address) throws DuplicateHouseAddress {
        if (smartHouses.get(address) != null)
            throw new DuplicateHouseAddress("Falha na criação da casa, endereço já existe");

        SmartHouse smartHouse = new SmartHouse(address);
        smartHouses.put(address, smartHouse);
    }

    public boolean addRoomToHouse(String address, String room) {
        smartHouses.get(address).addRoom(room);
        return true;
    }

    public void removeHouseByAddress(String address) throws SmartHouseNotFound, NoneHouseAvailable {
        if (address == null)
            throw new NoneHouseAvailable("Nenhuma casa foi criada ainda para ser removida");
        if (smartHouses.get(address) == null)
            throw new SmartHouseNotFound("Casa não encontrada");

        smartHouses.remove(address);
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
