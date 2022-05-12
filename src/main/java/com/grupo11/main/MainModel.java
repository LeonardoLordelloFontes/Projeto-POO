package com.grupo11.main;

import com.grupo11.energysupplier.EnergySupplierModel;
import com.grupo11.factory.FactoryModel;
import com.grupo11.smarthouse.SmartHouseModel;

import java.io.Serializable;

public class MainModel implements Serializable {

    private FactoryModel factory;
    private EnergySupplierModel energySuppliers;
    private SmartHouseModel smartHouses;

    private MainModel() {
    }

    /**
     * Cronstrutor da comunidade
     *
     * @param factory o model factory
     * @param energySuppliers o model energySuppliers
     * @param smartHouses o model smartHouses
     */
    public MainModel(FactoryModel factory,
                     EnergySupplierModel energySuppliers,
                     SmartHouseModel smartHouses) {
        this.factory = factory;
        this.energySuppliers = energySuppliers;
        this.smartHouses = smartHouses;
    }

    /**
     * Metodo que partilha o apontador para o model FactoryRepository
     *
     * @return o apontador para o model FactoryRepository
     */
    public FactoryModel getFactory() {
        return factory;
    }

    /**
     * Metodo que partilha o apontador para o model EnergySuppliers
     *
     * @return o apontador para o model EnergySuppliers
     */
    public EnergySupplierModel getEnergySuppliers() {
        return energySuppliers;
    }

    /**
     *  Metodo que partilha o apontador para o model SmartHouses
     *
     * @return o apontador para o model SmartHouses
     */
    public SmartHouseModel getSmartHouses() {
        return smartHouses;
    }

}
