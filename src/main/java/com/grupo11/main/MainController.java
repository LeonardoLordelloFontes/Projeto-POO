package com.grupo11.main;

import com.grupo11.community.Community;
import com.grupo11.energysupplier.EnergySupplierController;
import com.grupo11.factory.FactoryController;
import com.grupo11.simulation.SimulationController;
import com.grupo11.smarthouse.SmartHouseController;

import java.io.Serializable;

import static com.grupo11.main.MainMenu.mainMenu;

public class MainController implements Serializable {
    private Community community;

    private MainController() {
    }

    public MainController(Community community) {
        this.community = community;
    }

    public void runMainController() {
        mainController();
    }
    
    /*
    * Controlador Do Menu Principal do Projeto
    */
    
    public void mainController() {
        int choice = mainMenu();
        switch (choice) {
            case 1 -> {
                EnergySupplierController energySupplierController = new EnergySupplierController(community);
                energySupplierController.runEnergySupllierController();
            }
            case 2 -> {
                SmartHouseController smartHouseController = new SmartHouseController(community);
                smartHouseController.runSmartHouseController();
            }
            case 3 -> {
                FactoryController factoryController = new FactoryController(community);
                factoryController.runFactoryController();
            }
            /*
            case 4 -> {
                new SimulationController(this, true);
            }*/
            case 5 -> new SimulationController(this, false);
            // case 6 -> new StateController(this);
            case 7 -> System.exit(0);
        }
    }
}
