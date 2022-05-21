package com.grupo11.energysupplier;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.Serializable;
import java.util.Locale;

public class EnergySupplier implements Serializable {
    private String name;
    private final static double BASE_COST = 5;
    private final static double TAX = 0.05;
    private String formula;

    private EnergySupplier() {
    }

    public EnergySupplier(String name, String formula) {
        this.name = name;
        this.formula = formula;
    }

    public EnergySupplier(EnergySupplier Es) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        this.name = Es.getName();
        this.formula = Es.getFormula();
    }
    
    /**
     * @return getBaseCost do EnergySupplier
     */
    
    public double getBasecost() {
        return BASE_COST;
    }
    
    /**
     * @return name de um EnergySupplier
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name de um EnergySupplier
     */
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Tax de um EnergySupplier
     */
    
    public double getTax() {
        return TAX;
    }
    
    /**
     * @return formula de EnergySupplier 
     */

    public String getFormula() {return formula;}
    
    /** 
     * @param formula de EnergySupplier 
     */
    
    public void setFormula(String formula) {this.formula = formula;}
    
    /**
     * Calcula o consumo diário de um determinado dispositivo
     */
    
    public double deviceEnergyCostPerDay(String formula, double energyConsumption, int numberOfDevices) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        try {
            engine.eval("valorbase = " + String.valueOf(BASE_COST));
            engine.eval("imposto = " + String.valueOf(TAX));
            engine.eval("consumodispositivo = " + String.valueOf(energyConsumption));
            engine.eval("numerodispositivos = " + String.valueOf(numberOfDevices));
            return (double) engine.eval(formula.toLowerCase(Locale.ROOT));
        } catch (ScriptException e) {
            return -1;
        }
    }

    /**
     * Calcula o consumo por segundo de um determinado dispositivo
     *
     * @param formula
     * @param energyConsumption
     * @param numberOfDevices
     * @return devolve o consumo por segundo, ou -1, caso a formula seja inválida
     */

    public double deviceEnergyCostPerSecond(String formula, double energyConsumption, int numberOfDevices) {
        double cost = deviceEnergyCostPerDay(formula, energyConsumption, numberOfDevices);
        if (cost == -1) return -1;
        return cost/86400;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Energy Supplier: ");
        sb.append(this.name).append("\n").append("Total Cost: ").append(this.formula).append("\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergySupplier ES= (EnergySupplier) o;
        return ES.getName().equals(this.name) && ES.getFormula().equals(this.formula);
    }
    @Override
    public EnergySupplier clone() {
        return new EnergySupplier(this);
    }
}
