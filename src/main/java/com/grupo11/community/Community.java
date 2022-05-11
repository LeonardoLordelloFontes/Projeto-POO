package com.grupo11.community;

import com.grupo11.energysupplier.EnergySupplierRepository;
import com.grupo11.factory.FactoryRepository;
import com.grupo11.smarthouse.SmartHouseRepository;

public class Community {
    private FactoryRepository factory;
    private EnergySupplierRepository energySuppliers;
    private SmartHouseRepository smartHouses;

    private Community() {
    }

    public Community(FactoryRepository factory,
                     EnergySupplierRepository energySuppliers,
                     SmartHouseRepository smartHouses) {
        this.factory = factory;
        this.energySuppliers = energySuppliers;
        this.smartHouses = smartHouses;
    }

    public FactoryRepository getFactory() {
        return factory;
    }

    public EnergySupplierRepository getEnergySuppliers() {
        return energySuppliers;
    }

    public SmartHouseRepository getSmartHouses() {
        return smartHouses;
    }

}
