package com.grupoxx.EnergySupplier;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class EnergySupplier {
    private String name;
    private final static double BASE_COST = 5;
    private final static double TAX = 0.05;
    private String totalcost;

    public EnergySupplier() {
        this.name = "";
        this.totalcost ="";

    }

    public EnergySupplier(String name,int numdis, String totalcost) {
        this.name = name;
        this.totalcost = totalcost;

    }

    public EnergySupplier(EnergySupplier Es) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        this.name = Es.getName();
        this.totalcost = Es.getTotalcost();
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

    public String getTotalcost() {return totalcost;}
    public void setTotalcost(String totalcost) {this.totalcost = totalcost;}

    //TODO
    public double totalCostCal(String totalcost) throws ScriptException {
        // tratar de erros com formula Ex: Vazio
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        double resultado = (double) engine.eval(totalcost);
        return resultado;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Energy Supplier: ");
        sb.append(this.name).append("\n").append("Total Cost").append(this.totalcost);
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergySupplier ES= (EnergySupplier) o;
        return ES.getName().equals(this.name) && ES.getTotalcost().equals(this.totalcost);
    }
    @Override
    public EnergySupplier clone() {
        return new EnergySupplier(this);
    }
}
