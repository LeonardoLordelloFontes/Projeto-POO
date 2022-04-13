package com.grupoxx.simulation;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smarthouse.SmartHouse;

import java.time.LocalDate;
import java.util.List;

public abstract class Simulation {
    List<SmartHouse> smartHouses;
    LocalDate start;
    LocalDate end;

    public Simulation () {
        this.smartHouses = null;
        this.start = null;
        this.end = null;
    }

    public Simulation(Simulation simulation) {
        // TODO
    }

    public List<SmartHouse> getSmartHouses() {
        return smartHouses;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setSmartHouses(List<SmartHouse> smartHouses) {
        this.smartHouses = smartHouses;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
