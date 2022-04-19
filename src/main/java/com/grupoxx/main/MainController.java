package com.grupoxx.main;

import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.factory.Factory;
import com.grupoxx.smarthouse.SmartHouseController;
import com.grupoxx.smarthouse.SmartHouseRepository;

import static com.grupoxx.main.MainMenu.mainMenu;

public class MainController {
    private Factory factory;
    private EnergySupplierRepository energySupplierRepository;
    private SmartHouseRepository smartHouseRepository;
    public MainController() {
        this.factory = new Factory();
        this.energySupplierRepository = new EnergySupplierRepository();
        this.smartHouseRepository = new SmartHouseRepository();
        mainController();
    }

    public void mainController() {
        int choice = mainMenu();
        switch (choice) {
            case 1:
                break;
            case 2:
                new SmartHouseController(this);
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
                break;
            case 8:
                break;
        }
    }

    public EnergySupplierRepository getEnergySupplierRepository() {
        return energySupplierRepository;
    }

    public Factory getFactory() {
        return factory;
    }

    public SmartHouseRepository getSmartHouseRepository() {
        return smartHouseRepository;
    }
}
