package com.grupo11.main;

import com.grupo11.community.Community;
import com.grupo11.energysupplier.EnergySupplierController;
import com.grupo11.energysupplier.EnergySupplierRepository;
import com.grupo11.factory.FactoryRepository;
import com.grupo11.factory.FactoryController;
import com.grupo11.simulation.SimulationController;
import com.grupo11.simulation.SimulationMenu;
import com.grupo11.smarthouse.SmartHouseController;
import com.grupo11.smarthouse.SmartHouseRepository;

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
            case 4 -> {
                SimulationController simulationController = new SimulationController(community);
                simulationController.runManualSimulationController();
            }
            case 5 -> {
                SimulationController simulationController = new SimulationController(community);
                simulationController.runAutoSimulationController();
            }
            // case 6 -> new StateController(this);
            case 7 -> System.exit(0);
        }
    }
}
