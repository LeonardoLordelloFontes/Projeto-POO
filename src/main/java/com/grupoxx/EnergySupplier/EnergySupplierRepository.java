package com.grupoxx.EnergySupplier;

import com.grupoxx.smarthouse.SmartHouse;

import java.util.HashMap;
import java.util.List;
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

   public boolean updateEnergySupplierName(String oldName, String newName) {
        return true;
   }

   public boolean updateEnergySupplierFormula(String name, String newFormula) {
        return true;
   }

    public boolean removeEnergySupplier(String name, String formula) {
       try  {
           this.energySuppliers.remove(name);
           this.energySuppliers.remove(formula);
           return true;
       }
       catch (Exception e) {
           //Todo
           return  false;
       }
    }

    public List<EnergySupplier> findAllEnergySuppliers() {
        return null;
    }

    public Map<String, EnergySupplier> getEnergySuppliers() {
        return energySuppliers;
    }
}
