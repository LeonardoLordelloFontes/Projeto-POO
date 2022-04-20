package com.grupoxx.energysupplier;


import com.grupoxx.main.MainController;
import com.grupoxx.smarthouse.SmartHouseRepository;

public class EnergySupplierController {
    private final EnergySupplierMenu menu;
    private EnergySupplierRepository energySupplierRepository;
    private MainController mainController;
    private final SmartHouseRepository smartHouseRepository;

    public EnergySupplierController(MainController mainController) {
        this.mainController = mainController;
        this.menu = new EnergySupplierMenu();
        this.energySupplierRepository = mainController.getEnergySupplierRepository();
        this.smartHouseRepository = mainController.getSmartHouseRepository();
        energySupplierController();
    }

    public void energySupplierController() {
        switch (menu.MenuFornecedordeEnergia()) {
            case 1 ->addEnergySupplierController();
            case 2 ->removeEnergySupplierController();
            case 3 ->updateEnergySupplierController();
            case 4 ->this.mainController.mainController();
        }
    }

    public void addEnergySupplierController() {
        String[] input = menu.addEnergySupplierMenu();
        if (input == null) energySupplierController();
        else {
            this.energySupplierRepository.addEnergySupplier(input[0], input[1]);
            energySupplierController();
        }
    }
    public void updateEnergySupplierController() {
        String[] input = menu.updateEnergySupplierMenu();
        if(input == null) energySupplierController();
        else {
            this.energySupplierRepository.updateEnergySupplierName(smartHouseRepository,input[0],input[1]);
            this.energySupplierRepository.updateEnergySupplierFormula(input[1],input[2]);
            energySupplierController();
        }
    }
    public void removeEnergySupplierController() {
        String input = menu.removeEnergySupplierMenu();
        if(input == null) energySupplierController();
        else {
            this.energySupplierRepository.removeEnergySupplier(smartHouseRepository,input);
        }
    }
}
