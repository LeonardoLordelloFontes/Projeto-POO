package com.grupo11.simulation;

import com.grupo11.energysupplier.EnergySupplier;
import com.grupo11.energysupplier.EnergySupplierRepository;
import com.grupo11.main.MainController;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smarthouse.SmartHouse;
import com.grupo11.smarthouse.SmartHouseRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Simulation {

    private MainController mainController;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Invoicer> invoicers;

    public Simulation () {
    }

    public Simulation(MainController mainController, List<Invoicer> invoicers, LocalDateTime start, LocalDateTime end) {
        this.mainController = mainController;
        this.invoicers = invoicers;
        this.start = start;
        this.end = end;
        addInvoicers();
    }

    public void addInvoicers() {
        SmartHouseRepository houses = mainController.getSmartHouseRepository();
        EnergySupplierRepository energySuppliers = mainController.getEnergySupplierRepository();
        long simulationPeriod = ChronoUnit.SECONDS.between(start, end);
        for (SmartHouse smartHouse : houses.findAllSmartHouses()) {
            double totalCost = 0;
            for (String room : houses.findAllRoomsFromSmartHouse(smartHouse.getAddress())) {
                for (SmartDevice smartDevice : houses.findSmartDevicesByRoom(smartHouse.getAddress(), room).findAllSmartDevices()) {
                    if (smartDevice.getState().equals(SmartDevice.State.ON)) {
                        EnergySupplier energySupplier = energySuppliers.findEnergySupplierByName(smartHouse.getEnergySupplier());
                        totalCost += energySupplier.deviceEnergyCostPerSecond(
                                energySupplier.getFormula(),
                                smartDevice.energyConsumptionPerDay(),
                                houses.findNumberOfDevicesSmartHouse(smartHouse.getAddress())) * simulationPeriod;
                        if (totalCost < 0) {
                            System.out.println("Existe uma fórmula inválida no programa");
                            System.exit(1);
                        }
                    }
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
