package com.grupo11.simulation;

import com.grupo11.main.MainModel;
import com.grupo11.energysupplier.EnergySupplier;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smarthouse.SmartHouse;
import com.grupo11.smarthouse.SmartHouseModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ManualSimulation {

    private MainModel community;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Invoicer> invoicers;

    private ManualSimulation() {
    }

    public ManualSimulation(MainModel community, List<Invoicer> invoicers, LocalDateTime start, LocalDateTime end) {
        this.community = community;
        this.invoicers = invoicers;
        this.start = start;
        this.end = end;
    }

    /**
     * Inicia a simulação manual que vai gerar as faturas
     */

    public void runManualSimulation() {
        SmartHouseModel houses = community.getSmartHouses();
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

    /**
     * calcula o custo de um dispositivo específico durante um período de simulação
     *
     * @param smartHouse o endereço da casa onde está o dispositivo
     * @param smartDevice o dispositivo
     * @param simulationPeriod o período da simulação em segundos
     * @return o custo do dispositivo
     */

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
