package com.grupo11.main;

import com.grupo11.energysupplier.EnergySupplierController;
import com.grupo11.factory.FactoryController;
import com.grupo11.simulation.ParseLogs;
import com.grupo11.simulation.SimulationController;
import com.grupo11.smarthouse.SmartHouseController;
import com.grupo11.state.StateController;

import java.io.Serializable;

import static com.grupo11.main.MainView.mainMenu;

public class MainController implements Serializable {
    private MainModel community;

    private MainController() {
    }

    public MainController(MainModel community) {
        this.community = community;
    }

    public void runMainController() {
        mainController();
    }

    /**
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
            case 6 -> {
                SimulationController simulationController = new SimulationController(community);
                simulationController.loadLogsController();
            }
            case 7 -> {
                StateController stateController = new StateController(community);
                stateController.runStateController();
            }
            case 8 -> System.exit(0);
        }
    }
}
