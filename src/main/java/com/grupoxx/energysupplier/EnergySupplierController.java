package com.grupoxx.energysupplier;


import com.grupoxx.energysupplier.exception.EnergySupplierAlreadyExists;
import com.grupoxx.energysupplier.exception.EnergySupplierNotFound;
import com.grupoxx.main.MainController;
import com.grupoxx.smarthouse.SmartHouseRepository;

public class EnergySupplierController {
    private final EnergySupplierMenu menu;
    private final EnergySupplierRepository energySupplierRepository;
    private final MainController mainController;
    private final SmartHouseRepository smartHouseRepository;

    public EnergySupplierController(MainController mainController) {
        this.mainController = mainController;
        this.menu = new EnergySupplierMenu();
        this.energySupplierRepository = mainController.getEnergySupplierRepository();
        this.smartHouseRepository = mainController.getSmartHouseRepository();
        energySupplierController();
    }

    private void energySupplierController() {
        switch (menu.energySupplierMenu()) {
            case 1 -> addEnergySupplierController();
            case 2 -> removeEnergySupplierController();
            case 3 -> selectEnergySupplierToUpdate();
            case 4 -> listEnergySuppliers();
            case 5 -> this.mainController.mainController();
        }
    }

    private void addEnergySupplierController() {
        String[] input = menu.addEnergySupplierMenu();
        if (input == null) energySupplierController();
        else {
            this.energySupplierRepository.addEnergySupplier(input[0], input[1]);
            energySupplierController();
        }
    }

    private void removeEnergySupplierController() {
        String input = menu.removeEnergySupplierMenu(energySupplierRepository);
        if(input == null) energySupplierController();
        else {
            try {
                this.energySupplierRepository.removeEnergySupplier(smartHouseRepository,input);
                removeEnergySupplierController();
            } catch (EnergySupplierNotFound e) {
                System.out.println(e.getMessage());
                energySupplierController();
            }
        }
    }

    private void selectEnergySupplierToUpdate() {
        String selectedEnergySupplier = menu.selectEnergySupplierMenu(energySupplierRepository);
        if (selectedEnergySupplier == null) energySupplierController();
        else {
            updateEnergySupplierController(selectedEnergySupplier);
        }
    }

    private void updateEnergySupplierController(String name) {
        switch (menu.updateEnergySupplierMenu()) {
            case -1 -> updateEnergySupplierController(name);
            case 1 -> updateEnergySupplierNameController(name);
            case 2 -> updateEnergySupplierFormulaController(name);
            case 3 -> energySupplierController();
        }
    }

    private void updateEnergySupplierNameController(String oldName) {
        String newName = menu.updateEnergySupplierNameMenu();
        if (newName == null) updateEnergySupplierController(oldName);
        else {
            try {
                energySupplierRepository.updateEnergySupplierName(smartHouseRepository, oldName, newName);
                updateEnergySupplierController(newName);
            } catch (EnergySupplierAlreadyExists e) {
                System.out.println(e.getMessage());
                updateEnergySupplierNameController(oldName);
            }
        }
    }

    private void updateEnergySupplierFormulaController(String name) {
        String newFormula = menu.updateEnergySupplierFormulaMenu();
        if (newFormula == null) updateEnergySupplierController(name);
        else {
            energySupplierRepository.updateEnergySupplierFormula(name, newFormula);
            updateEnergySupplierController(name);
        }
    }

    private void listEnergySuppliers() {
        menu.listSmartHouses(energySupplierRepository.findAllEnergySuppliers());
        energySupplierController();
    }
}
