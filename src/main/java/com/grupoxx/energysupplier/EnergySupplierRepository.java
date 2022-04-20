package com.grupoxx.energysupplier;

import com.grupoxx.energysupplier.exception.EnergySupplierAlreadyExists;
import com.grupoxx.energysupplier.exception.EnergySupplierNotFound;
import com.grupoxx.smarthouse.SmartHouseRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnergySupplierRepository implements Serializable {
    private Map<String, EnergySupplier> energySuppliers; // Nome do energySupplier -> EnergySupplier
    public EnergySupplierRepository() {
        this.energySuppliers = new HashMap<>();
    }

    public EnergySupplier getEnergySupplierByName(String name) throws EnergySupplierNotFound {
        EnergySupplier energySupplier = energySuppliers.get(name);
        if (energySupplier == null)
            throw new EnergySupplierNotFound("O fonercedor de energia " + name + " não foi encontrado");
        return energySupplier;
    }

   public void addEnergySupplier(String name, String formula) throws EnergySupplierAlreadyExists {
       if (energySuppliers.get(name) != null)
           throw new EnergySupplierAlreadyExists("O Fonercedor de energia " + name + " já existe");
       EnergySupplier energySupplier = new EnergySupplier(name,formula);
       energySuppliers.put(name, energySupplier);
   }

   public void updateEnergySupplierName(SmartHouseRepository smartHouses, String oldName, String newName) throws EnergySupplierNotFound {
        if (energySuppliers.get(oldName) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + oldName + " não existe");
        EnergySupplier energySupplier = getEnergySupplierByName(oldName);
        energySupplier.setName(newName);
        energySuppliers.put(newName, energySupplier);
        energySuppliers.remove(oldName);
        smartHouses.findSmartHousesByEnergySupplier(oldName).forEach(smartHouse -> smartHouse.setEnergySupplier(newName));
   }

   public void updateEnergySupplierFormula(String name, String newFormula) throws EnergySupplierNotFound {
        if (energySuppliers.get(name) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + name + " não existe");
        energySuppliers.get(name).setFormula(newFormula);
    }

    public void removeEnergySupplier(SmartHouseRepository smartHouses, String name) throws EnergySupplierNotFound {
        if (energySuppliers.get(name) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + name + " não existe");
        energySuppliers.remove(name);
        smartHouses.findSmartHousesByEnergySupplier(name).forEach(smartHouse -> smartHouse.setEnergySupplier(null));
    }

    public List<EnergySupplier> findAllEnergySuppliers() {
        return new ArrayList<>(energySuppliers.values());
    }

    public Map<String, EnergySupplier> getEnergySuppliers() {
        return energySuppliers;
    }
}
