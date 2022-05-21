package com.grupo11.energysupplier;

import com.grupo11.energysupplier.exception.EnergySupplierAlreadyExists;
import com.grupo11.energysupplier.exception.EnergySupplierNotFound;
import com.grupo11.smarthouse.SmartHouseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnergySupplierModel implements Serializable {
    private final Map<String, EnergySupplier> energySuppliers; // Nome do energySupplier -> EnergySupplier
    public EnergySupplierModel() {
        this.energySuppliers = new HashMap<>();
    }
    
   /*
   ** Encontrar um EnergySupplier atraves do seu nome 
   */
    
   public EnergySupplier findEnergySupplierByName(String name) throws EnergySupplierNotFound {
        EnergySupplier energySupplier = energySuppliers.get(name);
        if (energySupplier == null)
            throw new EnergySupplierNotFound("O fonercedor de energia " + name + " não foi encontrado");
        return energySupplier;
    }
   
   /*
   ** Adicionar um EnergySupplier novo
   */
    
   public void addEnergySupplier(String name, String formula) throws EnergySupplierAlreadyExists {
       if (energySuppliers.get(name) != null)
           throw new EnergySupplierAlreadyExists("O Fonercedor de energia " + name + " já existe");
       EnergySupplier energySupplier = new EnergySupplier(name,formula);
       energySuppliers.put(name, energySupplier);
   }
   
   /* 
   ** Atualizar o nome de um EnergySupplier já existente
   */
    
   public void updateEnergySupplierName(SmartHouseModel smartHouses, String oldName, String newName) throws EnergySupplierNotFound {
        if (energySuppliers.get(oldName) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + oldName + " não existe");
        EnergySupplier energySupplier = findEnergySupplierByName(oldName);
        energySupplier.setName(newName);
        energySuppliers.put(newName, energySupplier);
        energySuppliers.remove(oldName);
        smartHouses.findSmartHousesByEnergySupplier(oldName).forEach(smartHouse -> smartHouse.setEnergySupplier(newName));
   }
   
   /*
   ** Atualizar a formula de um EnergySupplier já existente
   */
    
   public void updateEnergySupplierFormula(String name, String newFormula) throws EnergySupplierNotFound {
        if (energySuppliers.get(name) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + name + " não existe");
        energySuppliers.get(name).setFormula(newFormula);
    }
   
   /*
   ** Remover um EnergySupplier
   */
    
   public void removeEnergySupplier(SmartHouseModel smartHouses, String name) throws EnergySupplierNotFound {
        if (energySuppliers.get(name) == null)
            throw new EnergySupplierNotFound("O Fornecedor de energia " + name + " não existe");
        energySuppliers.remove(name);
        smartHouses.findSmartHousesByEnergySupplier(name).forEach(smartHouse -> smartHouse.setEnergySupplier(null));
    }
    /* Obter todos os EnergySuppliers Existentes
    **
    */
    public List<EnergySupplier> findAllEnergySuppliers() {
        return new ArrayList<>(energySuppliers.values());
    }
}
