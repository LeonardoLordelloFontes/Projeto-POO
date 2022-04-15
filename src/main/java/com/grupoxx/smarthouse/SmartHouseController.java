package com.grupoxx.smarthouse;

import com.grupoxx.main.MainController;
import com.grupoxx.smarthouse.exception.DuplicateHouseAddress;

import static com.grupoxx.smarthouse.SmartHouseMenu.*;

public class SmartHouseController {
    MainController mainController;
    public SmartHouseController(MainController mainController) {
        this.mainController = mainController;
        smartHouseController();
    }

    public void smartHouseController() {
        int choice = smartHouseMenu();
        switch (choice) {
            case 1:
                smartHouseAddController();
                break;
            case 2:
                smartHouseRemoveController();
                break;
            case 3:
                smartHouseUpdateController();
                break;
            case 4:
                smartHouseListController();
                break;
            case 5:
                new MainController(mainController);
                break;
        }
    }

    public void smartHouseAddController() {
        String[] input = smartHouseAddMenu();
        if (input == null) smartHouseController();
        else {
            try {
                mainController.getSmartHouseRepository().addSmartHouse(input[0]);
                if (input[1].equals("S")) smartHouseUpdateController(input[0]);
                else smartHouseController();
            } catch (DuplicateHouseAddress e) {
                System.out.println("Falha na criação da casa, endereço já existe");
                smartHouseAddController();
            }
        }
    }

    public void smartHouseRemoveController() {
        SmartHouseRepository smartHouseRepository = mainController.getSmartHouseRepository();
        String address = smartHouseSelectHousesMenu(smartHouseRepository);
        if (smartHouseRepository.getHouseByAddress(address) == null) {
            // TODO, não existe o endereço
        }
        else {
            smartHouseRepository.removeHouseByAddress(address);
        }
        smartHouseController();
    }

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
}
