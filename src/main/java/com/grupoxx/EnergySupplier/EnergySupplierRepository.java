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

    public boolean updateEnergySupplier(String nameOriginal, String newName, String formulaOriginal, String newFormula) {
        try {
            energySuppliers.get(0).setName(newName);
            energySuppliers.get(1).setTotalcost(newFormula);
            return true;
        }
        catch (Exception e) {
            // Todo, o EnergySupplier Original não existir;
               return false;

        }
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

    public Map<String, EnergySupplier> getEnergySuppliers() {
        return energySuppliers;
    }
}
