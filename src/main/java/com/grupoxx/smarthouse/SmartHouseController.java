package com.grupoxx.smarthouse;

import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.energysupplier.exception.EnergySupplierNotFound;
import com.grupoxx.factory.Factory;
import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.exception.HouseAddressAlreadyExists;
import com.grupoxx.smarthouse.exception.HouseNotFound;
import com.grupoxx.smarthouse.exception.RoomAlreadyExists;
import com.grupoxx.smarthouse.exception.RoomNotFound;

import java.io.Serializable;

public class SmartHouseController {
    private final MainController mainController;
    private final SmartHouseMenu menu;
    private final SmartHouseRepository smartHouses;
    private final Factory factory;
    private final EnergySupplierRepository energySuppliers;

    public SmartHouseController(MainController mainController) {
        this.mainController = mainController;
        this.menu = new SmartHouseMenu();
        this.smartHouses = mainController.getSmartHouseRepository();
        this.factory = mainController.getFactory();
        this.energySuppliers = mainController.getEnergySupplierRepository();
        smartHouseController();
    }

    /**
     * Controlador principal da classe SmartHouseController
     */

    private void smartHouseController() {
        switch (menu.smartHouse()) {
            case -1 -> smartHouseController();
            case 1 -> addSmartHouseController();
            case 2 -> removeSmartHouseController();
            case 3 -> selectSmartHouseController();
            case 4 -> listSmartHousesController();
            case 5 -> this.mainController.mainController();
        }
    }

    /**
     * Controlador da criação de casas
     */

    private void addSmartHouseController() {
        String address = menu.addSmartHouse();
        if (address == null) smartHouseController();
        else {
            try {
                smartHouses.addSmartHouse(address);
                addMoreInformationSmartHouseController(address);
            } catch (HouseAddressAlreadyExists e) {
                System.out.println(e.getMessage());
                smartHouseController();
            }
        }
    }

    /**
     * Controlador da opção que permite adicionar mais dados a casa na sua criação
     * @param address o endereço da casa criada
     */
    private void addMoreInformationSmartHouseController(String address) {
        String input = menu.addMoreInformationSmartHouse();
        switch (input) {
            case "S" -> updateSmartHouseController(address);
            case "N" -> smartHouseController();
            default -> addMoreInformationSmartHouseController(address);
        }
    }

    /**
     * Controlador da remoção de casas
     */

    private void removeSmartHouseController() {
        String address = menu.removeSmartHouse(smartHouses);
        if (address == null) smartHouseController();
        else {
            try {
                smartHouses.removeHouseByAddress(address);
                smartHouseController();
            } catch (HouseNotFound e) {
                System.out.println(e.getMessage());
                removeSmartHouseController();
            }
        }
    }

    /**
     * Controlador da seleção da casa que vai ser atualizada
     */

    private void selectSmartHouseController() {
        String address = menu.selectSmartHouse(smartHouses);
        if (address == null) smartHouseController();
        else {
           updateSmartHouseController(address);
        }
    }

    /**
     * Controlador da atualização dos dados de uma casa
     *
     * @param address o endereço da casa que desejamos atualizar os dados
     */

    private void updateSmartHouseController(String address) {
        switch (menu.updateSmartHouse()) {
            case -1 -> updateSmartHouseController(address);
            case 1 -> addRoomController(address);
            case 2 -> removeRoomController(address);
            case 3 -> selectSmartDeviceController(address);
            case 4 -> updateAddressController(address);
            case 5 -> updateEnergySupplierController(address);
            case 6 -> updateOwnerController(address);
            case 7 -> smartHouseController();
        }
    }

    /**
     * Controlador da listagem de casas
     */

    private void listSmartHousesController() {
        menu.listSmartHouses(smartHouses.findAllSmartHouses());
        smartHouseController();
    }

    /**
     * Controlador da criação de divisões de uma casa
     *
     * @param address o endereço da casa que desejamos adicionar divisões
     */

    private void addRoomController(String address) {
        String room = menu.addRoom();
        if (room == null) updateSmartHouseController(address);
        else {
            try {
                smartHouses.addRoomToHouse(address, room);
                addRoomController(address);
            } catch (RoomAlreadyExists e) {
                System.out.println(e.getMessage());
                addRoomController(address);
            }
        }
    }

    /**
     * Controlador da remoção de divisões de uma casa
     *
     * @param address o endereço da casa que desejamos remover divisões
     */

    private void removeRoomController(String address) {
        String room = menu.removeRoom(smartHouses, address);
        if (room == null) updateSmartHouseController(address);
        else {
            try {
                smartHouses.removeRoomFromHouse(address, room);
                removeRoomController(address);
            } catch (RoomNotFound e) {
                System.out.println(e.getMessage());
                removeRoomController(address);
            }
        }
    }

    /**
     * Controlador da seleção da divisão da casa que desejamos atualizar os dispositivos
     *
     * @param address o endereço da casa
     */

    private void selectSmartDeviceController(String address) {
        String room = menu.selectRoom(smartHouses, address);
        if (room == null) updateSmartHouseController(address);
        else {
            updateSmartDevicesController(address, room);
        }
    }

    /**
     * Controlador da atualização de dispositivos de uma divisão de uma casa
     *
     * @param address o endereço da casa
     * @param room a divisão da casa
     */

    private void updateSmartDevicesController(String address, String room) {
        switch (menu.updateSmartDevices()) {
            case -1 -> updateSmartDevicesController(address, room);
            case 1 -> addSmartDeviceController(address, room);
            case 2 -> removeSmartDeviceController(address, room);
            case 3 -> connectAllRoomSmartDevicesController(address, room);
            case 4 -> disconnectAllRoomSmartDevicesController(address, room);
            case 5 -> connectSmartDeviceController(address, room);
            case 6 -> disconnectSmartDeviceController(address, room);
            case 7 -> updateSmartHouseController(address);
        }
    }

    private void addSmartDeviceController(String address, String room) {
        String factoryCode = menu.addDevice(factory);
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            factory.setDeviceAvailability(factoryCode, false);
            SmartDevice smartDevice = factory.getSmartDeviceRepository().findSmartDeviceByFactoryCode(factoryCode);
            smartHouses.findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartDevice);
            addSmartDeviceController(address, room);
        }
    }

    private void removeSmartDeviceController(String address, String room) {
        String factoryCode = menu.removeSmartDeviceMenu(smartHouses.findSmartDevicesByRoom(address, room));
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            factory.setDeviceAvailability(factoryCode, true);
            smartHouses.findSmartDevicesByRoom(address, room).SmartDeviceRemove(factoryCode);
        }
    }

    private void connectAllRoomSmartDevicesController(String address, String room) {
        smartHouses.findSmartDevicesByRoom(address, room).findAllSmartDevices()
                .forEach(smartDevice -> smartDevice.setState(SmartDevice.State.ON));
        updateSmartDevicesController(address, room);
    }

    private void disconnectAllRoomSmartDevicesController(String address, String room) {
        smartHouses.findSmartDevicesByRoom(address, room).findAllSmartDevices()
                .forEach(smartDevice -> smartDevice.setState(SmartDevice.State.OFF));
        updateSmartDevicesController(address, room);
    }

    private void connectSmartDeviceController(String address, String room) {
        SmartDeviceRepository smartDevices = smartHouses.findSmartDevicesByRoom(address, room);
        String factoryCode = menu.removeSmartDeviceMenu(smartDevices);
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            smartDevices.findSmartDeviceByFactoryCode(factoryCode).setState(SmartDevice.State.ON);
            updateSmartDevicesController(address, room);
        }
    }

    private void disconnectSmartDeviceController(String address, String room) {
        SmartDeviceRepository smartDevices = smartHouses.findSmartDevicesByRoom(address, room);
        String factoryCode = menu.removeSmartDeviceMenu(smartDevices);
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            smartDevices.findSmartDeviceByFactoryCode(factoryCode).setState(SmartDevice.State.OFF);
            updateSmartDevicesController(address, room);
        }
    }

    private void updateAddressController(String oldAddress) {
        String newAddress = menu.updateAddress();
        if (newAddress == null) updateSmartHouseController(oldAddress);
        else {
            try {
                smartHouses.updateHouseAddress(oldAddress, newAddress);
                updateSmartHouseController(newAddress);
            } catch (HouseAddressAlreadyExists e) {
                System.out.println(e.getMessage());
                updateSmartHouseController(oldAddress);
            }
        }
    }

    private void updateEnergySupplierController(String address) {
        String newEnergySupplier = menu.updateEnergySupplierMenu(energySuppliers, smartHouses.findHouseByAddress(address).getEnergySupplier());
        if (newEnergySupplier == null) updateSmartHouseController(address);
        else {
            try {
                smartHouses.updateEnergySupplier(energySuppliers, address, newEnergySupplier);
                updateSmartHouseController(address);
            } catch (EnergySupplierNotFound e) {
                System.out.println(e.getMessage());
                updateSmartHouseController(address);
            }
        }
    }

    private void updateOwnerController(String address) {
        String[] newOwner = menu.updateOwner();
        if (newOwner == null) updateSmartHouseController(address);
        else {
            Owner owner = new Owner(newOwner[1], newOwner[0]);
            smartHouses.updateOwner(address, owner);
            updateSmartHouseController(address);
        }
    }
}
