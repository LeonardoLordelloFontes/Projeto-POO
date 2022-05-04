package com.grupoxx.main;

import com.grupoxx.energysupplier.EnergySupplierController;
import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.factory.Factory;
import com.grupoxx.factory.FactoryController;
import com.grupoxx.simulation.Invoicer;
import com.grupoxx.simulation.SimulationController;
import com.grupoxx.smarthouse.SmartHouse;
import com.grupoxx.smarthouse.SmartHouseController;
import com.grupoxx.smarthouse.SmartHouseRepository;
import com.grupoxx.state.State;
import com.grupoxx.state.StateController;

import javax.swing.text.html.InlineView;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    
    /*
    * Controlador Do Menu Principal do Projeto
    */
    
    public void mainController() {
        int choice = mainMenu();
        switch (choice) {
            case 1:
                new EnergySupplierController(this);
                break;
            case 2:
                new SmartHouseController(this);
                break;
            case 3:
                new FactoryController(this);
                break;
            case 4:
                new SimulationController(this, true);
                break;
            case 5:
                new SimulationController(this, false);
                break;
            case 6:
                new StateController(this);
                break;
            case 7:
                System.exit(0);
        }
    }
    
    /* 
    * @return EnergySupplierRepository
    */
    
    public EnergySupplierRepository getEnergySupplierRepository() {
        return energySupplierRepository;
    }
    
    /* 
    * @return Factory
    */
    
    public Factory getFactory() {
        return factory;
    }
    /* 
    * @return SmartHouseRepository
    */
    public SmartHouseRepository getSmartHouseRepository() {
        return smartHouseRepository;
    }
}
