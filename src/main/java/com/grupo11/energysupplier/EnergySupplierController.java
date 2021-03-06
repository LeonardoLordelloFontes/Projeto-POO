package com.grupo11.energysupplier;


import com.grupo11.main.MainModel;
import com.grupo11.energysupplier.exception.EnergySupplierAlreadyExists;
import com.grupo11.energysupplier.exception.EnergySupplierNotFound;
import com.grupo11.main.MainController;

public class EnergySupplierController {

    private MainModel community;
    private EnergySupplierView menu;

    private EnergySupplierController() {
    }

    public EnergySupplierController (MainModel community) {
        this.community = community;
        this.menu = new EnergySupplierView();
    }

    public void runEnergySupllierController() {
        energySupplierController();
    }

    /**
     * Controlador Principal da Classe EnergySupplier
     */

    private void energySupplierController() {
        switch (menu.energySupplierMenu()) {
            case 1 -> addEnergySupplierController();
            case 2 -> removeEnergySupplierController();
            case 3 -> selectEnergySupplierToUpdate();
            case 4 -> listEnergySuppliers();
            case 5 -> {
                MainController mainController = new MainController(community);
                mainController.runMainController();
            }
        }
    }
    
    /**
    * Controlador para Adicionar um EnergySupplier
    */
    
    private void addEnergySupplierController() {
        String[] input = menu.addEnergySupplierMenu();
        if (input == null) energySupplierController();
        else {
            try {
                this.community.getEnergySuppliers().addEnergySupplier(input[0], input[1]);
                energySupplierController();
            } catch(EnergySupplierAlreadyExists e){
                System.out.println(e.getMessage());
                energySupplierController();
            }
        }
    }

    /**
     * Controlador para Remover um EnergySupplier
     */
    
    private void removeEnergySupplierController() {
        String input = menu.removeEnergySupplierMenu(community.getEnergySuppliers());
        if(input == null) energySupplierController();
        else {
            try {
                this.community.getEnergySuppliers().removeEnergySupplier(community.getSmartHouses(),input);
                removeEnergySupplierController();
            } catch (EnergySupplierNotFound e) {
                System.out.println(e.getMessage());
                energySupplierController();
            }
        }
    }
    
    /**
     * Controlador da sele????o do EnergySupplier que vai ser Atualizado
     */

    private void selectEnergySupplierToUpdate() {
        String selectedEnergySupplier = menu.selectEnergySupplierMenu(community.getEnergySuppliers());
        if (selectedEnergySupplier == null) energySupplierController();
        else {
            try {
                updateEnergySupplierController(selectedEnergySupplier);
            } catch (EnergySupplierNotFound e) {
                System.out.println(e.getMessage());
                energySupplierController();
            }
        }
    }
    
    /**
     * Controlador da atualiza????o dos dados de um EnergySupplier
     */

    private void updateEnergySupplierController(String name) {
        switch (menu.updateEnergySupplierMenu()) {
            case -1 -> updateEnergySupplierController(name);
            case 1 -> updateEnergySupplierNameController(name);
            case 2 -> updateEnergySupplierFormulaController(name);
            case 3 -> energySupplierController();
        }
    }
    
    /**
     * Controlador da atualiza????o do nome de um EnergySupplier
     */
    
    private void updateEnergySupplierNameController(String oldName) {
        String newName = menu.updateEnergySupplierNameMenu();
        if (newName == null) updateEnergySupplierController(oldName);
        else {
            try {
                community.getEnergySuppliers().updateEnergySupplierName(community.getSmartHouses(), oldName, newName);
                updateEnergySupplierController(newName);
            } catch (EnergySupplierAlreadyExists e) {
                System.out.println(e.getMessage());
                updateEnergySupplierNameController(oldName);
            }
        }
    }

    /**
     * Controlador da atualiza????o da formula de um EnergySupplier
     */
    
    private void updateEnergySupplierFormulaController(String name) {
        String newFormula = menu.updateEnergySupplierFormulaMenu();
        if (newFormula == null) updateEnergySupplierController(name);
        else {
            community.getEnergySuppliers().updateEnergySupplierFormula(name, newFormula);
            updateEnergySupplierController(name);
        }
    }

    private void listEnergySuppliers() {
        menu.listSmartHouses(community.getEnergySuppliers().findAllEnergySuppliers());
        energySupplierController();
    }
}
