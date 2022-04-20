package com.grupoxx.simulation;

import com.grupoxx.main.MainController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    private long simulationPeriod() {
        return ChronoUnit.SECONDS.between(start, end);
    }


    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
