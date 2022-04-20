package com.grupoxx.energysupplier;

import java.io.Serializable;

public class EnergySupplierController {

    private EnergySupplierRepository energySupplierRepository;

    public EnergySupplierController(EnergySupplierRepository energySupplierRepository) {
        this.energySupplierRepository = energySupplierRepository;
        energySupplierController();
    }
    public void energySupplierController() {
        System.out.println(energySupplierRepository.getEnergySuppliers());
        int choice = EnergySupplierMenu.MenuFornecedordeEnergia();
        switch (choice) {
            case 1:

            case 2:

            case 3:

            case 4:
                //todo
        }
    }

/*
    public void addEnergySupplierController() {
        String[] input = EnergySupplierMenu.AdicionarFornecedordeEnergia();
        if ( input.equals(null) ) energySupplierController();

        this.energySupplierRepository.addEnergySupplier(input[0], input[1]);
        energySupplierController();
    }
    public void updateEnergySupplierNameController() {
        String[] input = EnergySupplierMenu.updateEnergySupplier();
        if(input.equals(null)) energySupplierController();

        // this.energySupplierRepository.updateEnergySupplierName(input[0],input[1]);
        energySupplierController();
    }
    public void updateEnergySupplierFormulaController() {
        String[] input = EnergySupplierMenu.AtualizarFornecedordeEnergia();
        if(input.equals(null)) energySupplierController();

        // this.energySupplierRepository.updateEnergySupplierFormula(input[0],input[1]);
        energySupplierController();
    }
    public void removeEnergySupplierController() {
        String[] input = EnergySupplierMenu.AtualizarFornecedordeEnergia();
        if(input.equals(null)) energySupplierController();

        this.energySupplierRepository.removeEnergySupplier(input[0],input[1];
    }
    */

}
