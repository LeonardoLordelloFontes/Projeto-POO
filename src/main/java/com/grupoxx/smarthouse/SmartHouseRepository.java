package com.grupoxx.smarthouse;
import com.grupoxx.EnergySupplier.EnergySupplierRepository;
import com.grupoxx.EnergySupplier.exception.EnergySupplierNotFound;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.exception.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartHouseRepository {

    private final Map<String, SmartHouse> smartHouses;

    public SmartHouseRepository() {
        this.smartHouses = new HashMap<>();
    }

    public SmartHouse getHouseByAddress(String address) throws HouseNotFound {
        SmartHouse smartHouse = smartHouses.get(address);
        if (smartHouse == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        return smartHouse;
    }

    public SmartDeviceRepository getSmartDevicesByRoom(String address, String room) throws HouseNotFound, RoomNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        SmartDeviceRepository smartDeviceRepository = smartHouses.get(address).getSmartDevices().get(room);
        if (smartDeviceRepository == null)
            throw new RoomNotFound("A divisão com o nome " + room + " não foi encontrada");
        return smartDeviceRepository;
    }

    public void addSmartHouse(String address) throws DuplicateHouseAddress {
        if (smartHouses.get(address) != null)
            throw new DuplicateHouseAddress("A casa com o endereço " + address + " já existe");
        SmartHouse smartHouse = new SmartHouse(address);
        smartHouses.put(address, smartHouse);
    }

    public void addRoomToHouse(String address, String room) throws HouseNotFound, RoomAlreadyExists {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        if (smartHouses.get(address).getSmartDevices().get(room) != null)
            throw new RoomAlreadyExists("A divisão " + room + " já existe nesta casa");
        smartHouses.get(address).getSmartDevices().put(room, new SmartDeviceRepository());
    }

    public void removeRoomFromHouse(String address, String room) throws HouseNotFound, RoomNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        if (smartHouses.get(address).getSmartDevices().get(room) == null)
            throw new RoomNotFound("A divisão com o nome " + room + " não foi encontrada");
        smartHouses.get(address).getSmartDevices().remove(room);
    }

    public void removeHouseByAddress(String address) throws HouseNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        smartHouses.remove(address);
    }

    public void updateHouseAddress(String oldAddress, String newAddress) throws HouseNotFound, DuplicateHouseAddress {
        if (smartHouses.get(oldAddress) == null)
            throw new HouseNotFound("A casa com o endereço " + oldAddress + " não foi encontrada");
        if (smartHouses.get(newAddress) != null)
            throw new DuplicateHouseAddress("A casa com o endereço " + newAddress + " já existe");
        smartHouses.get(oldAddress).setAddress(newAddress);
    }

    public void updateEnergySupplier(EnergySupplierRepository energySupplierRepository,
                                     String address,
                                     String newEnergySupplier) throws HouseNotFound, EnergySupplierNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        if (energySupplierRepository.getEnergySuppliers().get(newEnergySupplier) == null)
            throw new EnergySupplierNotFound("O fonecedor de energia " + newEnergySupplier + " não foi encontrado");
        smartHouses.get(address).setEnergySupplier(newEnergySupplier);
    }

    public void updateOwner(String address, Owner newOwner) throws HouseNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        smartHouses.get(address).setOwner(newOwner);
    }

    public List<SmartHouse> findAllSmartHouses() {
        return new ArrayList<>(smartHouses.values());
    }
}
