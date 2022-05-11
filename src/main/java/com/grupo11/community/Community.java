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

    /**
     * Cronstrutor da comunidade
     *
     * @param factory o model factory
     * @param energySuppliers o model energySuppliers
     * @param smartHouses o model smartHouses
     */
    public Community(FactoryRepository factory,
                     EnergySupplierRepository energySuppliers,
                     SmartHouseRepository smartHouses) {
        this.factory = factory;
        this.energySuppliers = energySuppliers;
        this.smartHouses = smartHouses;
    }

    /**
     * Metodo que partilha o apontador para o model FactoryRepository
     *
     * @return o apontador para o model FactoryRepository
     */
    public FactoryRepository getFactory() {
        return factory;
    }

    /**
     * Metodo que partilha o apontador para o model EnergySuppliers
     *
     * @return o apontador para o model EnergySuppliers
     */
    public EnergySupplierRepository getEnergySuppliers() {
        return energySuppliers;
    }

    /**
     *  Metodo que partilha o apontador para o model SmartHouses
     *
     * @return o apontador para o model SmartHouses
     */
    public SmartHouseRepository getSmartHouses() {
        return smartHouses;
    }

}
