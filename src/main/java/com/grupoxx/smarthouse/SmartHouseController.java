package com.grupoxx.smarthouse;

import com.grupoxx.EnergySupplier.exception.EnergySupplierNotFound;
import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smarthouse.exception.HouseAddressAlreadyExists;
import com.grupoxx.smarthouse.exception.HouseNotFound;
import com.grupoxx.smarthouse.exception.RoomAlreadyExists;
import com.grupoxx.smarthouse.exception.RoomNotFound;

import static com.grupoxx.smarthouse.SmartHouseMenu.*;

public class SmartHouseController {
    private final MainController mainController;
    private final SmartHouseMenu menu;

    public SmartHouseController(MainController mainController) {
        this.mainController = mainController;
        this.menu = new SmartHouseMenu();
        smartHouse();
    }

    /**
     * Controlador principal da classe SmartHouseController
     */

    private void smartHouse() {
        int choice = menu.smartHouse();
        if (choice == -1) smartHouse();
        switch (choice) {
            case 1 -> addSmartHouse();
            case 2 -> removeSmartHouse();
            case 3 -> updateSmartHouse();
            case 4 -> listSmartHouses();
            case 5 -> this.mainController.mainController();
        }
    }

    /**
     * Controlador da criação de casas
     */

    private void addSmartHouse() {
        String address = menu.addSmartHouse();
        if (address == null) smartHouse();
        else {
            try {
                mainController.getSmartHouseRepository().addSmartHouse(address);
                addMoreInformationSmartHouse(address);
            } catch (HouseAddressAlreadyExists e) {
                System.out.println(e.getMessage());
                smartHouse();
            }
        }
    }

    /**
     * Controlador da opção que permite adicionar mais dados a casa na sua criação
     * @param address o endereço da casa criada
     */
    private void addMoreInformationSmartHouse(String address) {
        String input = menu.addMoreInformationSmartHouse();
        switch (input) {
            case "S" -> updateSmartHouse(address);
            case "N" -> smartHouse();
            default -> addMoreInformationSmartHouse(address);
        }
    }

    /**
     * Controlador da remoção de casas
     */

    private void removeSmartHouse() {
        SmartHouseRepository smartHouseRepository = mainController.getSmartHouseRepository();
        String address = menu.selectSmartHouse(smartHouseRepository);
        if (address == null) smartHouse();
        else {
            try {
                smartHouseRepository.removeHouseByAddress(address);
                smartHouse();
            } catch (HouseNotFound e) {
                System.out.println(e.getMessage());
                removeSmartHouse();
            }
        }
    }

    /**
     * Controlador da seleção da casa que vai ser atualizada
     */

    private void updateSmartHouse() {
        String address = menu.selectSmartHouse(mainController.getSmartHouseRepository());
        if (address == null) smartHouse();
        else {
           updateSmartHouse(address);
        }
    }

    /**
     * Controlador da atualização dos dados de uma casa
     *
     * @param address o endereço da casa que desejamos atualizar os dados
     */

    private void updateSmartHouse(String address) {
        int option = menu.updateSmartHouse();
        if (option == -1) updateSmartHouse(address);
        else {
            switch (option) {
                case 1 -> addRoom(address);
                case 2 -> removeRoom(address);
                case 3 -> updateSmartDevices(address);
                case 4 -> updateAddress(address);
                case 5 -> updateEnergySupplier(address);
                case 6 -> updateOwner(address);
                case 7 -> smartHouse();
            }
        }
    }

    /**
     * Controlador da listagem de casas
     */

    private void listSmartHouses() {
        menu.listSmartHouses(mainController.getSmartHouseRepository().findAllSmartHouses());
        smartHouse();
    }

    /**
     * Controlador da criação de divisões de uma casa
     *
     * @param address o endereço da casa que desejamos adicionar divisões
     */

    private void addRoom(String address) {
        String room = menu.addRoom();
        if (room == null) updateSmartHouse(address);
        else {
            try {
                mainController.getSmartHouseRepository().addRoomToHouse(address, room);
                addRoom(address);
            } catch (RoomAlreadyExists e) {
                System.out.println(e.getMessage());
                addRoom(address);
            }
        }
    }

    /**
     *
     * @param address
     */

    private void removeRoom(String address) {
        String room = menu.removeRoom(mainController.getSmartHouseRepository(), address);
        if (room == null) updateSmartHouse(address);
        else {
            try {
                mainController.getSmartHouseRepository().removeRoomFromHouse(address, room);
                removeRoom(address);
            } catch (RoomNotFound e) {
                System.out.println(e.getMessage());
                removeRoom(address);
            }
        }
    }

    private void updateSmartDevices(String address) {
        String room = menu.selectRoom(mainController.getSmartHouseRepository(), address);
        if (room == null) updateSmartHouse(address);
        else {
            updateSmartDevices(address, room);
        }
    }

    private void updateSmartDevices(String address, String room) {
        int option = menu.updateSmartDevices();
        if (option == -1) updateSmartDevices(address, room);
        else {
            switch (option) {
                case 1 -> addDevice(address, room);
                case 2 -> removeDevice(address, room);
                case 7 -> updateSmartHouse(address);
            }
        }
    }

    // precisa de verificação
    private void addDevice(String address, String room) {
        String factoryCode = menu.addDevice(mainController.getFactory());
        if (factoryCode == null) updateSmartDevices(address, room);
        else {
            mainController.getFactory().setDeviceAvailability(factoryCode, false);
            SmartDevice smartDevice = mainController.getFactory().getSmartDeviceRepository()
                    .findSmartDeviceByFactoryCode(factoryCode);
            mainController.getSmartHouseRepository().findSmartDevicesByRoom(address, room)
                    .addSmartDevice(factoryCode, smartDevice);
            addDevice(address, room);
        }
    }


    // TODO

    private void removeDevice(String address, String room) {
        // TODO
    }

    private void updateAddress(String oldAddress) {
        String newAddress = menu.updateAddress();
        if (newAddress == null) updateSmartHouse(oldAddress);
        else {
            try {
                mainController.getSmartHouseRepository().updateHouseAddress(oldAddress, newAddress);
                updateSmartHouse(newAddress);
            } catch (HouseAddressAlreadyExists e) {
                System.out.println(e.getMessage());
                updateSmartHouse(oldAddress);
            }
        }
    }

    private void updateEnergySupplier(String address) {
        String newEnergySupplier = menu.updateEnergySupplier();
        if (newEnergySupplier == null) updateSmartHouse(address);
        else {
            try {
                mainController.getSmartHouseRepository().
                        updateEnergySupplier(mainController.getEnergySupplierRepository(), address, newEnergySupplier);
                updateSmartHouse(address);
            } catch (EnergySupplierNotFound e) {
                System.out.println(e.getMessage());
                updateSmartHouse(address);
            }
        }
    }

    private void updateOwner(String address) {
        String[] newOwner = menu.updateOwner();
        if (newOwner == null) updateSmartHouse(address);
        else {
            Owner owner = new Owner(newOwner[0], newOwner[1]);
            mainController.getSmartHouseRepository().updateOwner(address, owner);
            updateSmartHouse(address);
        }
    }
}
