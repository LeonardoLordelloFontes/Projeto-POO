package com.grupoxx.EnergySupplier;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class EnergySupplier {
    private String EnergySup;
    private final static double BASE_COST = 5;
    private double dailypriceofone;
    private final static double TAX = 0.05;
    private int numdis;
    private String totalcost;

    public EnergySupplier() {
        this.EnergySup = "";
        this.dailypriceofone = BASE_COST / TAX;
        this.numdis = 1;
        this.totalcost ="";

    }

    public EnergySupplier(String Es,double dailypriceofone, int numdis, String totalcost) {
        this.EnergySup = Es;
        this.dailypriceofone = dailypriceofone;
        this.numdis = numdis;
        this.totalcost = totalcost;

    }


    public EnergySupplier(EnergySupplier Es) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        this.EnergySup = Es.getEnergySup();
        this.dailypriceofone = Es.getDailypriceofone();
        this.numdis = Es.getNumdis();
        this.totalcost = Es.getTotalcost();
    }



    public double getBasecost() {
        return BASE_COST;
    }

    public double getDailypriceofone() {
        return dailypriceofone;
    }
    public void setDailypriceofone(double dailypriceofone) {
        this.dailypriceofone = dailypriceofone;
    }

    public String getEnergySup() {
        return EnergySup;
    }
    public void setEnergySup(String energySup) {
        EnergySup = energySup;
    }

    public double getTax() {
        return TAX;
    }

    public int getNumdis() {return numdis;}
    public void setNumdis(int numeroDiapostivos) {
        numdis = numeroDiapostivos;}

    public String getTotalcost() {return totalcost;}
    public void setTotalcost(String totalcost) {this.totalcost = totalcost;}

    //TODO
    public void totalCostCal(String totalcost) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");

    }
    //TODO
    @Override
    public String toString() {

        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergySupplier ES= (EnergySupplier) o;
        return ES.getEnergySup().equals(this.EnergySup) && ES.getNumdis() == this.numdis && ES.getDailypriceofone() == this.dailypriceofone && ES.getTotalcost().equals(this.totalcost);
    }
    @Override
    public EnergySupplier clone() {
        return new EnergySupplier(this);
    }
}
