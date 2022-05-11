package com.grupo11.simulation;

import com.grupo11.community.Community;
import com.grupo11.datastatus.DataStatusController;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smarthouse.SmartHouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class AutomaticSimulation {
    private Community community;
    private List<Invoicer> invoicers;

    private AutomaticSimulation() {
    }

    public AutomaticSimulation(Community community, List<Invoicer> invoicers) {
        this.community = community;
        this.invoicers = invoicers;
    }

    public void runAutomaticSimulation(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime start = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(",");
            LocalDateTime lastChange = LocalDateTime.parse(data[0], dateTimeFormatter);
            ManualSimulation manualSimulation = new ManualSimulation(community, invoicers, start, lastChange);
            manualSimulation.runManualSimulation();
            if (data.length > 1) mainOptions(data, lastChange);
            start = lastChange;
        }
        filterInvoicers();
    }

    private void mainOptions(String[] data, LocalDateTime lastChange) {
        if (data[1].startsWith("casa:")) {
            houseOptions(data, lastChange);
        }
        else if (data[1].startsWith("fornecedor:")) {
            energySupplierOptions(data);
        }
    }

    private void houseOptions(String[] data, LocalDateTime lastChange) {
        if (data[2].startsWith("dispositivo:")) {
            deviceOptions(data, data[2].substring(12), lastChange);
        }
        else if (data[2].startsWith("fornecedor:")) {
            community.getSmartHouses().updateEnergySupplier(community.getEnergySuppliers(),
                                                            data[1].substring(5),
                                                            data[2].substring(11));
        }
    }

    private void deviceOptions(String[] data, String factoryCode, LocalDateTime lastChange) {
        if (data[3].equals("setOn")) {
            community.getFactory().getSmartDeviceRepository()
                    .findSmartDeviceByFactoryCode(factoryCode).switchConnection(lastChange, SmartDevice.State.ON);
        }
        else if (data[3].equals("setOff")) {
            community.getFactory().getSmartDeviceRepository()
                    .findSmartDeviceByFactoryCode(factoryCode).switchConnection(lastChange, SmartDevice.State.OFF);
        }
    }

    private void energySupplierOptions(String[] data) {
        if (data[2].equals("alteraValorDesconto")) {
            community.getEnergySuppliers().updateEnergySupplierFormula(data[1].substring(11), data[3]);
        }
    }

    private void filterInvoicers() {
        for (SmartHouse house : community.getSmartHouses().findAllSmartHouses()) {
            double totalCost = 0;
            List<Invoicer> houseInvoicers = invoicers.stream()
                    .filter(invoicer -> invoicer.getHouseAddress().equals(house.getAddress())).toList();
            for (Invoicer invoicer : houseInvoicers) {
                totalCost += invoicer.getTotalCost();
            }
            invoicers.removeIf(invoicer -> invoicer.getHouseAddress().equals(house.getAddress()));
            Invoicer newInvoicer = new Invoicer(house.getOwner(), house.getEnergySupplier(), totalCost, house.getAddress());
            invoicers.add(newInvoicer);
        }
    }
}
