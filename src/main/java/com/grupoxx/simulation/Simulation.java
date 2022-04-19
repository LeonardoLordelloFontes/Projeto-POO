package com.grupoxx.simulation;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.EnergySupplier.EnergySupplierRepository;
import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smarthouse.SmartHouse;
import com.grupoxx.smarthouse.SmartHouseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Simulation {

    private MainController mainController;
    private LocalDateTime start;
    private LocalDateTime end;

    public Simulation () {
    }

    public Simulation(MainController mainController, LocalDateTime start, LocalDateTime end) {
        this.mainController = mainController;
        this.start = start;
        this.end = end;
    }

    public Simulation(Simulation simulation) {
        // TODO
    }


    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
