package com.grupoxx.smarthouse;

import com.grupoxx.menu.MainMenu;

public class SmartHouseController {
    private SmartHouseRepository smartHouseRepository;
    public SmartHouseController(SmartHouseRepository smartHouseRepository) {
        this.smartHouseRepository = smartHouseRepository;
        smartHouseController();
    }

    public void smartHouseController() {
        int choice = MainMenu.menuCasa();
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}
