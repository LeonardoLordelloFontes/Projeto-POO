package com.grupoxx.simulation;

import com.grupoxx.main.MainController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SimulationController {
    private boolean isManualSimulation;
    private SimulationMenu menu;
    private MainController mainController;
    private List<Invoicer> invoicers;

    public SimulationController(MainController mainController, boolean isManualSimulation) {
        this.mainController = mainController;
        this.isManualSimulation = isManualSimulation;
        this.menu = new SimulationMenu();
        this.invoicers = new ArrayList<>();
        if (isManualSimulation) manualSimulationController();
        else autoSimulationController();
    }

    private void manualSimulationController() {
        LocalDateTime[] dates = menu.manualSimulationMenu();
        if (dates == null) manualSimulationController();
        else {
            new Simulation(mainController, invoicers, dates[0], dates[1]);
            // falta redirecionar para o DataStatusController
        }
    }

    private void autoSimulationController() {

    }
}
