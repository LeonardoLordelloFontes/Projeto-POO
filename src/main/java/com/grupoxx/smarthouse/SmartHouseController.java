package com.grupoxx.smarthouse;

import com.grupoxx.main.MainController;
import com.grupoxx.main.MainMenu;

import static com.grupoxx.main.MainMenu.mainMenu;
import static com.grupoxx.smarthouse.SmartHouseMenu.smartHouseAddMenu;
import static com.grupoxx.smarthouse.SmartHouseMenu.smartHouseMenu;

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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                new MainController(mainController);
                break;
        }
    }
}
