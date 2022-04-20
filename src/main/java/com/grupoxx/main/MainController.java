package com.grupoxx.main;

import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.factory.Factory;
import com.grupoxx.smarthouse.SmartHouse;
import com.grupoxx.smarthouse.SmartHouseController;
import com.grupoxx.smarthouse.SmartHouseRepository;
import com.grupoxx.state.State;
import com.grupoxx.state.StateController;

import java.io.IOException;
import java.io.Serializable;

import static com.grupoxx.main.MainMenu.mainMenu;

public class MainController implements Serializable {
    private Factory factory;
    private EnergySupplierRepository energySupplierRepository;
    private SmartHouseRepository smartHouseRepository;
    public MainController() {
        this.factory = new Factory();
        this.energySupplierRepository = new EnergySupplierRepository();
        this.smartHouseRepository = new SmartHouseRepository();
        mainController();
    }

    public MainController(MainController mainController) {
        this.factory = mainController.getFactory();
        this.energySupplierRepository = mainController.getEnergySupplierRepository();
        this.smartHouseRepository = mainController.getSmartHouseRepository();
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
                new StateController(this);
                break;
            case 7:
                System.exit(0);
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
