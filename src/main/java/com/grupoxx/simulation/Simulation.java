package com.grupoxx.simulation;

import com.grupoxx.energysupplier.EnergySupplier;
import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smarthouse.SmartHouse;
import com.grupoxx.smarthouse.SmartHouseRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public void addInvoicers() {
        SmartHouseRepository houses = mainController.getSmartHouseRepository();
        EnergySupplierRepository energySuppliers = mainController.getEnergySupplierRepository();
        List<Invoicer> invoicers = mainController.getInvoicers();
        long simulationPeriod = ChronoUnit.SECONDS.between(start, end);
        for (SmartHouse smartHouse : houses.findAllSmartHouses()) {
            double totalCost = 0;
            for (String room : houses.findAllRoomsFromSmartHouse(smartHouse.getAddress())) {
                for (SmartDevice smartDevice : houses.findSmartDevicesByRoom(smartHouse.getAddress(), room).findAllSmartDevices()) {
                    EnergySupplier energySupplier = energySuppliers.findEnergySupplierByName(smartHouse.getEnergySupplier());
                    totalCost += energySupplier.deviceEnergyCostPerSecond(
                            energySupplier.getFormula(),
                            smartDevice.energyConsumptionPerDay(),
                            houses.findNumberOfDevicesSmartHouse(smartHouse.getAddress())) * simulationPeriod;
                }
            }
            Invoicer invoicer = new Invoicer(smartHouse.getOwner(), smartHouse.getEnergySupplier(), totalCost, smartHouse.getAddress());
            invoicers.add(invoicer);
        }
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
