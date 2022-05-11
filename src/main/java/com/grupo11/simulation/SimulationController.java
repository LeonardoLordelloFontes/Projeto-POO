package com.grupo11.simulation;

import com.grupo11.datastatus.DataStatusController;
import com.grupo11.main.MainController;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smarthouse.SmartHouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationController {
    private boolean isManualSimulation;
    private SimulationMenu menu;
    private MainController mainController;
    private List<Invoicer> invoicers;

    public SimulationController(MainController mainController, boolean isManualSimulation) {
        this.mainController = mainController;
        this.isManualSimulation = isManualSimulation;
        this.menu = new SimulationMenu();
        this.invoicers = new ArrayList<>();
        if (isManualSimulation) manualSimulationController();
        else autoSimulationController();
    }

    private void manualSimulationController() {
        LocalDateTime[] dates = menu.manualSimulationMenu();
        if (dates == null) manualSimulationController();
        else {
            new Simulation(mainController, invoicers, dates[0], dates[1]);
            new DataStatusController(mainController, invoicers);
        }
    }

    private void autoSimulationController() {
        String filePath = menu.autoSimulationMenu();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime start = LocalDateTime.parse(scanner.nextLine(), dateTimeFormatter);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                LocalDateTime end = LocalDateTime.parse(data[0], dateTimeFormatter);
                new Simulation(mainController, invoicers, start, end);
                if (data.length != 1) {
                    if (data[1].startsWith("casa")) {
                        if (data[2].startsWith("dispositivo")) {
                            String factoryCode = data[2].substring(11);
                            if (data[3].equals("setOn")) mainController.getFactory().getSmartDeviceRepository()
                                    .findSmartDeviceByFactoryCode(factoryCode).switchConnection(end, SmartDevice.State.ON);
                            else if (data[3].equals("setOff")) {
                                mainController.getFactory().getSmartDeviceRepository()
                                        .findSmartDeviceByFactoryCode(factoryCode).switchConnection(end, SmartDevice.State.OFF);
                            }
                        } else if (data[2].startsWith("fornecedor")) {
                            mainController.getSmartHouseRepository().
                                    updateEnergySupplier(mainController.getEnergySupplierRepository(),
                                            data[1].substring(4),
                                            data[2].substring(10));
                        }
                    } else if (data[1].startsWith("fornecedor")) {
                        mainController.getEnergySupplierRepository()
                                .updateEnergySupplierFormula(data[1].substring(10), data[2]);
                    }
                    start = end;
                }
            }
            for (SmartHouse house : mainController.getSmartHouseRepository().findAllSmartHouses()) {
                double totalCost = 0;
                List<Invoicer> houseInvoicers = invoicers.stream()
                        .filter(invoicer -> invoicer.getHouseAddress().equals(house.getAddress())).toList();
                for (Invoicer invoicer : houseInvoicers) {
                    totalCost += invoicer.getTotalCost();
                    System.out.println(totalCost);
                }
                invoicers.removeIf(invoicer -> invoicer.getHouseAddress().equals(house.getAddress()));
                Invoicer newInvoicer = new Invoicer(house.getOwner(), house.getEnergySupplier(), totalCost, house.getAddress());
                invoicers.add(newInvoicer);
            }
            new DataStatusController(mainController, invoicers);

        } catch (FileNotFoundException e) {
            System.out.print("Arquivo não encontrado! Digite o caminho válido");
            autoSimulationController();
        }
    }


}
