package com.grupoxx.EnergySupplier;

import javax.swing.plaf.nimbus.State;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class EnergySupplier {
    private String EnergySup;
    private final double BASE_COST = 5;
    private double PrecoDiaPorDispositivo;
    private final double TAX = 0.05;
    private int NumeroDispostivos;
    private String custototal;

    public EnergySupplier() {
        this.EnergySup = "";
        this.PrecoDiaPorDispositivo = BASE_COST / TAX;
        this.NumeroDispostivos = 1;
        this.custototal = "";

    }

    public EnergySupplier(EnergySupplier Es) {
        this.EnergySup = "";
        this.PrecoDiaPorDispositivo = BASE_COST / TAX;
        this.NumeroDispostivos = 1;
        this.custototal = "";
    }


    public double getBasecost() {
        return BASE_COST;
    }

    public double getPrecoDiaPorDispositivo() {
        return PrecoDiaPorDispositivo;
    }

    public void setPrecoDiaPorDispositivo(double precoDiaPorDispositivo) {
        PrecoDiaPorDispositivo = precoDiaPorDispositivo;
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
    public int getNumeroDispostivos() {
        return NumeroDispostivos;
    }

    public void setNumeroDispostivos(int numeroDiapostivos) {
        NumeroDispostivos = numeroDiapostivos;
    }

}
