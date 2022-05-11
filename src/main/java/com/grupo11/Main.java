package com.grupo11;

import com.grupo11.community.Community;
import com.grupo11.energysupplier.EnergySupplierRepository;
import com.grupo11.factory.FactoryRepository;
import com.grupo11.main.MainController;
import com.grupo11.smarthouse.SmartHouseRepository;

public class Main {
    public static void main(String[] args) {
        FactoryRepository factory = new FactoryRepository();
        EnergySupplierRepository energySuppliers = new EnergySupplierRepository();
        SmartHouseRepository smartHouses = new SmartHouseRepository();
        Community community = new Community(factory, energySuppliers, smartHouses);
        MainController mainController = new MainController(community);
        mainController.runMainController();
    }
}

