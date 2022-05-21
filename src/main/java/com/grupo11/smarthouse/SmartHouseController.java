package com.grupo11.smarthouse;

import com.grupo11.main.MainModel;
import com.grupo11.energysupplier.exception.EnergySupplierNotFound;
import com.grupo11.main.MainController;
import com.grupo11.smartdevice.*;
import com.grupo11.smarthouse.exception.HouseAddressAlreadyExists;
import com.grupo11.smarthouse.exception.HouseNotFound;
import com.grupo11.smarthouse.exception.RoomAlreadyExists;
import com.grupo11.smarthouse.exception.RoomNotFound;
import java.util.function.Predicate;

public class SmartHouseController {

    private MainModel community;
    private SmartHouseView menu;

    private SmartHouseController() {
    }

    public SmartHouseController(MainModel community) {
        this.community = community;
        this.menu = new SmartHouseView();
    }

    public void runSmartHouseController() {
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
            case 5 -> {
                MainController mainController = new MainController(community);
                mainController.runMainController();
            }
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
                community.getSmartHouses().addSmartHouse(address);
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
        String address = menu.removeSmartHouse(community.getSmartHouses());
        if (address == null) smartHouseController();
        else {
            try {
                community.getSmartHouses().removeHouseByAddress(address);
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
        String address = menu.selectSmartHouse(community.getSmartHouses());
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
        menu.listSmartHouses(community.getSmartHouses().findAllSmartHouses());
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
                community.getSmartHouses().addRoomToHouse(address, room);
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
        String room = menu.removeRoom(community.getSmartHouses(), address);
        if (room == null) updateSmartHouseController(address);
        else {
            try {
                community.getSmartHouses().removeRoomFromHouse(address, room);
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
        String room = menu.selectRoom(community.getSmartHouses(), address);
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
            case 3 -> connectAllRoomSmartDevicesControllerMaster(address, room);
            case 4 -> disconnectAllRoomSmartDevicesControllerMaster(address, room);
            case 5 -> connectSmartDeviceController(address, room);
            case 6 -> disconnectSmartDeviceController(address, room);
            case 7 -> updateSmartHouseController(address);
        }
    }

    /**
     * Controlador da adição de um dispositivo a uma divisão de uma casa
     *
     * @param address o endereço da casa
     * @param room a divisão da casa
     */

    private void addSmartDeviceController(String address, String room) {
        String factoryCode = menu.addDevice(community.getFactory());
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            community.getFactory().setDeviceAvailability(factoryCode, false);
            SmartDevice smartDevice = community.getFactory().getSmartDeviceRepository().findSmartDeviceByFactoryCode(factoryCode);
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartDevice);
            addSmartDeviceController(address, room);
        }
    }

    /**
     * Controlador da remoção de um dispositivo que pertence a uma divisão de uma casa
     *
     * @param address endereço da casa
     * @param room divisão da casa
     */

    private void removeSmartDeviceController(String address, String room) {
        String factoryCode = menu.removeSmartDeviceMenu(community.getSmartHouses().findSmartDevicesByRoom(address, room));
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            community.getFactory().setDeviceAvailability(factoryCode, true);
            community.getSmartHouses().findSmartDevicesByRoom(address, room).SmartDeviceRemove(factoryCode);
            updateSmartDevicesController(address, room);
        }
    }

    /**
     * método auxiliar para selecionar o tipo de dispositivo
     *
     * @return o predicado correspondente a opção escolhida
     */

    private Predicate<SmartDevice> selectSmartDevicesState() {
        int d = menu.updateSmartDevicesState();
        Predicate<SmartDevice> p1 = x -> x instanceof SmartDeviceBulb;
        Predicate<SmartDevice> p2 = x -> x instanceof SmartDeviceSpeaker;
        Predicate<SmartDevice> p3 = x -> x instanceof SmartDeviceCamera;
        Predicate<SmartDevice> p4 = x -> true;
        Predicate<SmartDevice> selected = null;
        switch (d) {
            case 1 -> selected = p1;
            case 2 -> selected = p2;
            case 3 -> selected = p3;
            case 4 -> selected = p4;
        }
        return selected;
    }

    /**
     * Controlador principal de desligar dispositivos
     *
     * @param address o endereço da casa onde está o dispositivo
     * @param room o nome da divisão da casa onde está o dispositivo
     */

    private void disconnectAllRoomSmartDevicesControllerMaster(String address, String room) {
        Predicate<SmartDevice> p = selectSmartDevicesState();
        if (p == null) updateSmartDevicesController(address, room);
        else {
            disconnectAllRoomSmartDevicesController(address, room, p);
        }
    }

    /**
     * Controlador principal de ligar dispositivos
     *
     * @param address o endereço da casa onde está o dispositivo
     * @param room o nome da divisão da casa onde está o dispositivo
     */

    private void connectAllRoomSmartDevicesControllerMaster(String address, String room) {
        Predicate<SmartDevice> p = selectSmartDevicesState();
        if (p == null) updateSmartDevicesController(address, room);
        else {
            connectAllRoomSmartDevicesController(address, room, p);
        }
    }

    /**
     * Controlador para ligar todos os dispositivos de uma divisão
     *
     * @param address endereço da casa
     * @param room nome da divisão da casa
     * @param p o predicado correspondente ao tipo de dispositivo
     */

    private void connectAllRoomSmartDevicesController(String address, String room, Predicate<SmartDevice> p) {
        if (community.getSmartHouses().findSmartDevicesByRoom(address, room).findAllSmartDevices().stream().anyMatch(x -> x instanceof SmartDeviceBulb)) {
            connectAllSmartBulbController(address, room);
        }
        community.getSmartHouses().findSmartDevicesByRoom(address,room).smartDeviceState(p, SmartDevice.State.ON);
        updateSmartDevicesController(address, room);
    }

    /**
     * Controlador para desligar todos os dispositivos de uma divisão
     *
     * @param address endereço da casa
     * @param room nome da divisão da casa
     * @param p o predicado correspondente ao tipo de dispositivo
     */

    private void disconnectAllRoomSmartDevicesController(String address, String room, Predicate<SmartDevice> p) {
        community.getSmartHouses().findSmartDevicesByRoom(address, room).smartDeviceState(p, SmartDevice.State.OFF);
        updateSmartDevicesController(address, room);
    }

    /**
     * Controlador para ligar um dispositivo específico
     *
     * @param address endereço da casa
     * @param room a divisão onde o dispositivo se encontra
     */

    private void connectSmartDeviceController(String address, String room) {
        SmartDeviceModel smartDevices = community.getSmartHouses().findSmartDevicesByRoom(address, room);
        String factoryCode = menu.removeSmartDeviceMenu(smartDevices);
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            SmartDevice smartDevice = smartDevices.findSmartDeviceByFactoryCode(factoryCode);
            smartDevice.setState(SmartDevice.State.ON);
            if (smartDevice instanceof SmartDeviceBulb) connectSmartBulbController((SmartDeviceBulb) smartDevice, address, room);
            else updateSmartDevicesController(address, room);
        }
    }

    /**
     * Controlador para desligar um dispositivo específico
     *
     * @param address endereço da casa
     * @param room a divisão onde o dispositivo se encontra
     */

    private void disconnectSmartDeviceController(String address, String room) {
        SmartDeviceModel smartDevices = community.getSmartHouses().findSmartDevicesByRoom(address, room);
        String factoryCode = menu.removeSmartDeviceMenu(smartDevices);
        if (factoryCode == null) updateSmartDevicesController(address, room);
        else {
            smartDevices.findSmartDeviceByFactoryCode(factoryCode).setState(SmartDevice.State.OFF);
            updateSmartDevicesController(address, room);
        }
    }

    /**
     * Controlador para ligar um dispositivo específico do tipo SmartDeviceBulb numa tonalidade específica
     *
     * @param smartBulb o dispositivo que desejamos ligar
     * @param address o endereço da casa
     * @param room o nome da divisão da casa onde o dispostivo se encontra
     */

    private void connectSmartBulbController(SmartDeviceBulb smartBulb, String address, String room) {
        switch (menu.selectBulbToneMenu()) {
            case -1 -> connectSmartBulbController(smartBulb, address, room);
            case 1 -> {
                smartBulb.setTone(SmartDeviceBulb.Tone.Neutral);
                updateSmartDevicesController(address, room);
            }
            case 2 -> {
                smartBulb.setTone(SmartDeviceBulb.Tone.Warm);
                updateSmartDevicesController(address, room);
            }
            case 3 -> {
                smartBulb.setTone(SmartDeviceBulb.Tone.Cold);
                updateSmartDevicesController(address, room);
            }
        }
    }

    /**
     * Controlador para ligar todos os dispositivos do tipo SmartDeviceBulb numa tonalidade específica
     *
     * @param address o endereço da casa
     * @param room o nome da divisão da casa onde o dispostivo se encontra
     */

    private void connectAllSmartBulbController(String address, String room) {
        switch (menu.selectBulbToneMenu()) {
            case -1 -> connectSmartDeviceController(address, room);
            case 1 -> {
                community.getSmartHouses().findSmartDevicesByRoom(address,room).smartDeviceTone(SmartDeviceBulb.Tone.Neutral);
            }
            case 2 -> {
                community.getSmartHouses().findSmartDevicesByRoom(address,room).smartDeviceTone(SmartDeviceBulb.Tone.Warm);
            }
            case 3 -> {
                community.getSmartHouses().findSmartDevicesByRoom(address,room).smartDeviceTone(SmartDeviceBulb.Tone.Cold);
            }
        }
    }

    /**
     * Controlador para alterar o endereço de uma cas
     *
     * @param oldAddress o endereço da casa que deseja alterar
     */

    private void updateAddressController(String oldAddress) {
        String newAddress = menu.updateAddress();
        if (newAddress == null) updateSmartHouseController(oldAddress);
        else {
            try {
                community.getSmartHouses().updateHouseAddress(oldAddress, newAddress);
                updateSmartHouseController(newAddress);
            } catch (HouseAddressAlreadyExists e) {
                System.out.println(e.getMessage());
                updateSmartHouseController(oldAddress);
            }
        }
    }

    /**
     * Controlador para atualizar o fornecedor de energia de uma casa
     *
     * @param address o endereço da casa
     */

    private void updateEnergySupplierController(String address) {
        String newEnergySupplier = menu.updateEnergySupplierMenu(community.getEnergySuppliers(), community.getSmartHouses().findHouseByAddress(address).getEnergySupplier());
        if (newEnergySupplier == null) updateSmartHouseController(address);
        else {
            try {
                community.getSmartHouses().updateEnergySupplier(community.getEnergySuppliers(), address, newEnergySupplier);
                updateSmartHouseController(address);
            } catch (EnergySupplierNotFound e) {
                System.out.println(e.getMessage());
                updateSmartHouseController(address);
            }
        }
    }

    /**
     * Controlador para atualizar o proprietário de uma casa
     *
     * @param address o endereço da casa
     */

    private void updateOwnerController(String address) {
        String[] newOwner = menu.updateOwner();
        if (newOwner == null) updateSmartHouseController(address);
        else {
            Owner owner = new Owner(newOwner[1], newOwner[0]);
            community.getSmartHouses().updateOwner(address, owner);
            updateSmartHouseController(address);
        }
    }
}
