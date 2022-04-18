package com.grupoxx.smarthouse;

import com.grupoxx.EnergySupplier.exception.EnergySupplierNotFound;
import com.grupoxx.main.MainController;
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
        switch (choice) {
            case 1 -> addSmartHouse();
            case 2 -> removeSmartHouse();
            case 3 -> updateSmartHouse();
            case 4 -> listSmartHouses();
            case 5 -> new MainController(mainController);
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


    private void updateSmartHouse() {
        String address = menu.selectSmartHouse(mainController.getSmartHouseRepository());
        if (address == null) smartHouse();
        else {
           updateSmartHouse(address);
        }
    }

    private void updateSmartHouse(String address) {
        int option = menu.updateSmartHouse();
        if (option == -1) updateSmartHouse(address);
        else {
            switch (option) {
                case 1 -> addRoom(address);
                case 3 -> removeRoom(address);
                case 5 -> updateAddress(address);
                case 6 -> updateEnergySupplier(address);
                case 7 -> updateOwner(address);
                case 9 -> smartHouse();
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

    private void addDevice(String address, String room) {
        String factoryCode = menu.addDevice(mainController.getFactory());
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

        /*
    public void smartHouseListController() {
        mainController.getSmartHouseRepository().findAllSmartHouses().forEach(System.out::println);
        smartHouseController();
    }

    public void smartHouseUpdateController() {
        String address = smartHouseSelectHousesMenu(mainController.getSmartHouseRepository());
        smartHouseUpdateController(address);
    }

    public void smartHouseUpdateController(String address) {
        if (address == null) smartHouseController();
        else {
            int choice = smartHouseUpdateMenu();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    smartHouseUpdateOwnerController(address);
                    break;
                case 8:
                    break;
                case 9:
                    smartHouseController();
                    break;
            }
        }
    }

    public void smartHouseUpdateOwnerController(String address) {
        String[] input = smartHouseUpdateOwnerMenu();
        if (input != null) {
            Owner newOwner = new Owner(input[0], input[1]);
            mainController.getSmartHouseRepository().updateOwner(address, newOwner);
        }
        smartHouseUpdateController(address);
    }

         */
}
