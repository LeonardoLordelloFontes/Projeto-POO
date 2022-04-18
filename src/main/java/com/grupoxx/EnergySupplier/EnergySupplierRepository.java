package com.grupoxx.EnergySupplier;

import com.grupoxx.EnergySupplier.exception.EnergySupplierAlreadyExists;
import com.grupoxx.EnergySupplier.exception.EnergySupplierNotFound;
import com.grupoxx.smarthouse.SmartHouse;
import com.grupoxx.smarthouse.exception.HouseNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnergySupplierRepository {
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
       energySuppliers.put(name,energySupplier);
   }

   public void updateEnergySupplierName(String oldName, String newName) throws EnergySupplierNotFound {
        if (energySuppliers.get(oldName) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + oldName + " não existe");
        energySuppliers.get(oldName).setName(newName);
   }

   public void updateEnergySupplierFormula(String name, String newFormula) throws EnergySupplierNotFound {
        if (energySuppliers.get(name) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + name + " não existe");
        energySuppliers.get(name).setTotalcost(newFormula);
    }

    public void removeEnergySupplier(String name, String formula) throws EnergySupplierNotFound {
        if (energySuppliers.get(name) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + name + " não existe");
        this.energySuppliers.remove(name);
    }

    public List<EnergySupplier> findAllEnergySuppliers() {
        return new ArrayList<>(energySuppliers.values());
    }

    public Map<String, EnergySupplier> getEnergySuppliers() {
        return energySuppliers;
    }
}
