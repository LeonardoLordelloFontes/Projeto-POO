package com.grupoxx.simulation;

import com.grupoxx.main.MainController;

import java.time.LocalDateTime;

public class SimulationController {
    private boolean isManualSimulation;
    private SimulationMenu menu;
    private MainController mainController;

    public SimulationController(MainController mainController, boolean isManualSimulation) {
        this.mainController = mainController;
        this.isManualSimulation = isManualSimulation;
        this.menu = new SimulationMenu();
        if (isManualSimulation) manualSimulationController();
        else autoSimulationController();
    }

    private void manualSimulationController() {
        LocalDateTime[] dates = menu.manualSimulationMenu();
        if (dates == null) manualSimulationController();
        else {
            new Simulation(mainController, dates[0], dates[1]);
            // falta redirecionar para o DataStatusController
        }
    }

    private void autoSimulationController() {

    }
}
