package com.grupo11;

import com.grupo11.main.MainModel;
import com.grupo11.energysupplier.EnergySupplierModel;
import com.grupo11.factory.FactoryModel;
import com.grupo11.main.MainController;
import com.grupo11.smarthouse.SmartHouseModel;

public class Main {
    public static void main(String[] args) {
        FactoryModel factory = new FactoryModel();
        EnergySupplierModel energySuppliers = new EnergySupplierModel();
        SmartHouseModel smartHouses = new SmartHouseModel();
        MainModel community = new MainModel(factory, energySuppliers, smartHouses);
        MainController mainController = new MainController(community);
        mainController.runMainController();
    }
}

