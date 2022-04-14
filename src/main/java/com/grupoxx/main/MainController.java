package com.grupoxx.main;

import com.grupoxx.EnergySupplier.EnergySupplierRepository;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.SmartHouseController;
import com.grupoxx.smarthouse.SmartHouseRepository;

import java.util.Scanner;

import static com.grupoxx.main.MainMenu.mainMenu;

public class MainController {
    private SmartDeviceRepository smartDeviceRepository;
    private EnergySupplierRepository energySupplierRepository;
    private SmartHouseRepository smartHouseRepository;
    public MainController() {
        this.smartDeviceRepository = new SmartDeviceRepository();
        this.energySupplierRepository = new EnergySupplierRepository();
        this.smartHouseRepository = new SmartHouseRepository();
        mainController();
    }

    public MainController(MainController mainController) {
        this.smartDeviceRepository = mainController.getSmartDeviceRepository();
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

    public SmartDeviceRepository getSmartDeviceRepository() {
        return smartDeviceRepository;
    }

    public SmartHouseRepository getSmartHouseRepository() {
        return smartHouseRepository;
    }
}
