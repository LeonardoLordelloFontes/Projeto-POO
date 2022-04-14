package com.grupoxx.EnergySupplier;

import com.grupoxx.menu.Menu;

public class EnergySupplierController {

    private EnergySupplierRepository energySupplierRepository;

    public EnergySupplierController(EnergySupplierRepository energySupplierRepository) {
        this.energySupplierRepository = energySupplierRepository;
        energySupplierController();
    }
    public void energySupplierController() {
        System.out.println(energySupplierRepository.getEnergySuppliers());
        int choice = Menu.MenuFornecedordeEnergia();
        switch (choice) {
            case 1:
                addEnergySupplierController();
        }
    }

    public void addEnergySupplierController() {
        String[] input = Menu.AdicionarFornecedordeEnergia();
        if ( input.equals(null) ) energySupplierController();

        this.energySupplierRepository.addEnergySupplier(input[0], input[1]);
        energySupplierController();
    }
}
