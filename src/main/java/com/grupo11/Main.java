package com.grupo11;

import com.grupo11.main.MainModel;
import com.grupo11.energysupplier.EnergySupplierRepository;
import com.grupo11.factory.FactoryModel;
import com.grupo11.main.MainController;
import com.grupo11.smarthouse.SmartHouseRepository;

public class Main {
    public static void main(String[] args) {
        FactoryModel factory = new FactoryModel();
        EnergySupplierRepository energySuppliers = new EnergySupplierRepository();
        SmartHouseRepository smartHouses = new SmartHouseRepository();
        MainModel community = new MainModel(factory, energySuppliers, smartHouses);
        MainController mainController = new MainController(community);
        mainController.runMainController();
    }
}

