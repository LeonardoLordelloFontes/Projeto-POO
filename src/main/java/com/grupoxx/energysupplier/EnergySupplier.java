package com.grupoxx.energysupplier;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.Serializable;

public class EnergySupplier implements Serializable {
    private String name;
    private final static double BASE_COST = 5;
    private final static double TAX = 0.05;
    private String formula;

    public EnergySupplier() {
        this.name = null;
        this.formula = null;
    }

    public EnergySupplier(String name,String totalcost) {
        this.name = name;
        this.formula = totalcost;

    }

    public EnergySupplier(EnergySupplier Es) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        this.name = Es.getName();
        this.formula = Es.getFormula();
    }

    public double getBasecost() {
        return BASE_COST;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getTax() {
        return TAX;
    }

    public String getFormula() {return formula;}
    public void setFormula(String formula) {this.formula = formula;}

    public double deviceEnergyCostPerDay(String formula, double energyConsumption, int numberOfDevices) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        try {
            engine.eval("ValorBase = " + String.valueOf(BASE_COST));
            engine.eval("Imposto = " + String.valueOf(TAX));
            engine.eval("ConsumoDispositivo = " + String.valueOf(energyConsumption));
            engine.eval("numeroDispositivos = " + String.valueOf(numberOfDevices));
            return (double) engine.eval(formula);
        } catch (ScriptException e) {
            return -1;
        }
    }

    public double deviceEnergyCostPerSecond(String formula, double energyConsumption, int numberOfDevices) {
        double cost = deviceEnergyCostPerDay(formula, energyConsumption, numberOfDevices);
        if (cost == -1) return -1;
        return cost/86400;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Energy Supplier: ");
        sb.append(this.name).append("\n").append("Total Cost").append(this.formula);
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
