package com.grupo11.simulation;

import com.grupo11.main.MainModel;
import com.grupo11.datastatus.DataStatusController;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SimulationController {

    private MainModel community;
    private SimulationMenu menu;
    private List<Invoicer> invoicers;

    private SimulationController() {
    }

    public SimulationController(MainModel community) {
        this.community = community;
        this.menu = new SimulationMenu();
        this.invoicers = new ArrayList<>();
    }

    public void runManualSimulationController() {
        LocalDateTime[] dates = menu.manualSimulationMenu();
        if (dates == null) runManualSimulationController();
        else {
            ManualSimulation manualSimulation = new ManualSimulation(community, invoicers, dates[0], dates[1]);
            manualSimulation.runManualSimulation();
            DataStatusController dataStatusController = new DataStatusController(community, invoicers);
            dataStatusController.runDataStatusController();
        }
    }

    public void runAutoSimulationController() {
        String filePath = menu.autoSimulationMenu();
        try {
            AutomaticSimulation automaticSimulation = new AutomaticSimulation(community, invoicers);
            automaticSimulation.runAutomaticSimulation(filePath);
            DataStatusController dataStatusController = new DataStatusController(community, invoicers);
            dataStatusController.runDataStatusController();
        }
        catch (FileNotFoundException e) {
            System.out.print("Arquivo não encontrado! Digite um caminho válido");
            runAutoSimulationController();
        }
    }


}
