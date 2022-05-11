package com.grupo11.simulation;

import com.grupo11.community.Community;
import com.grupo11.energysupplier.EnergySupplier;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smarthouse.SmartHouse;
import com.grupo11.smarthouse.SmartHouseRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ManualSimulation {

    private Community community;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Invoicer> invoicers;

    private ManualSimulation() {
    }

    public ManualSimulation(Community community, List<Invoicer> invoicers, LocalDateTime start, LocalDateTime end) {
        this.community = community;
        this.invoicers = invoicers;
        this.start = start;
        this.end = end;
    }

    public void runManualSimulation() {
        SmartHouseRepository houses = community.getSmartHouses();
        long simulationPeriod = ChronoUnit.SECONDS.between(start, end);
        for (SmartHouse smartHouse : houses.findAllSmartHouses()) {
            double totalCost = 0;
            for (String room : houses.findAllRoomsFromSmartHouse(smartHouse.getAddress())) {
                for (SmartDevice smartDevice : houses.findSmartDevicesByRoom(smartHouse.getAddress(), room).findAllSmartDevices()) {
                    totalCost += getSmartDeviceCost(smartHouse, smartDevice, simulationPeriod);
                }
            }
            Invoicer invoicer = new Invoicer(smartHouse.getOwner(), smartHouse.getEnergySupplier(), totalCost, smartHouse.getAddress());
            invoicers.add(invoicer);
        }
    }

    private double getSmartDeviceCost(SmartHouse smartHouse, SmartDevice smartDevice, long simulationPeriod) {
        double totalCost = 0;
        if (smartDevice.getState().equals(SmartDevice.State.ON)) {
            EnergySupplier energySupplier = community.getEnergySuppliers().findEnergySupplierByName(smartHouse.getEnergySupplier());
            totalCost = energySupplier.deviceEnergyCostPerSecond(energySupplier.getFormula(),
                    smartDevice.energyConsumptionPerDay(),
                    community.getSmartHouses().findNumberOfDevicesSmartHouse(smartHouse.getAddress())) * simulationPeriod;
        }
        if (totalCost < 0) {
            System.out.println("Existe uma fórmula inválida no programa");
            System.exit(1);
        }
        return totalCost;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
