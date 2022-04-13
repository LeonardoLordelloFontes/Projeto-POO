package com.grupoxx.EnergySupplier;

import com.grupoxx.smarthouse.SmartHouse;

import java.util.HashMap;
import java.util.Map;

public class EnergySupplierRepository {
    private Map<String, EnergySupplier> energySuppliers;
    public EnergySupplierRepository() {
        this.energySuppliers = new HashMap<>();
    }

   public boolean addEnergySupplier(String name, String formula) {
       try {
           EnergySupplier energySupplier = new EnergySupplier(name, formula);
           energySuppliers.put(name, energySupplier);
           return true;
       } catch (Exception e) {
           // TODO, por exemplo se já existir este endereço
           return false;
       }
   }

    public Map<String, EnergySupplier> getEnergySuppliers() {
        return energySuppliers;
    }
}
