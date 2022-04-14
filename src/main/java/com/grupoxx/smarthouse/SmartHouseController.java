package com.grupoxx.smarthouse;

import com.grupoxx.main.MainController;
import com.grupoxx.main.MainMenu;
import com.grupoxx.simulation.Simulation;

import static com.grupoxx.main.MainMenu.mainMenu;
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
        if (input != null) {
            mainController.getSmartHouseRepository().addSmartHouse(input[0]);
            if (input[1].equals("S")) smartHouseUpdateController();
        }
        smartHouseController();
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

    }
}
