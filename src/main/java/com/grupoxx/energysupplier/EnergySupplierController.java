package com.grupoxx.energysupplier;


import com.grupoxx.main.MainController;
import com.grupoxx.smarthouse.SmartHouseRepository;

public class EnergySupplierController {
    private final EnergySupplierMenu menu;
    private EnergySupplierRepository energySupplierRepository;
    private MainController mainController;
    private final SmartHouseRepository smartHouseRepository;

    public EnergySupplierController(EnergySupplierMenu menu, EnergySupplierRepository energySupplierRepository, SmartHouseRepository smartHouseRepository) {
        this.menu = menu;
        this.energySupplierRepository = energySupplierRepository;
        this.smartHouseRepository = smartHouseRepository;
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
        if ( input.equals(null) ) energySupplierController();

        this.energySupplierRepository.addEnergySupplier(input[0], input[1]);
        energySupplierController();
    }
    public void updateEnergySupplierController() {
        String[] input = menu.updateEnergySupplierMenu();
        if(input.equals(null)) energySupplierController();

        this.energySupplierRepository.updateEnergySupplierName(smartHouseRepository,input[0],input[1]);
        this.energySupplierRepository.updateEnergySupplierFormula(input[1],input[2]);

        energySupplierController();
    }
    public void removeEnergySupplierController() {
        String input = menu.removeEnergySupplierMenu();
        if(input.equals(null)) energySupplierController();

        this.energySupplierRepository.removeEnergySupplier(smartHouseRepository,input);
    }


}
