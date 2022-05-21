package com.grupo11.simulation;

import com.grupo11.main.MainModel;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smarthouse.SmartHouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AutomaticSimulation {
    private MainModel community;
    private List<Invoicer> invoicers;

    private AutomaticSimulation() {
    }

    public AutomaticSimulation(MainModel community, List<Invoicer> invoicers) {
        this.community = community;
        this.invoicers = invoicers;
    }

    /**
     * Inicia a simulação automática
     *
     * @param filePath o caminho do arquivo da simulação automática
     * @throws FileNotFoundException
     */

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

    /**
     * Analisa o segundo elemento de uma linha separada por vírgulas e redireciona conforme o que aparece
     *
     * @param data a linha que está a ser analisada
     * @param lastChange a data da ultima mudança que ocorreu durante a simulação automática
     */

    private void mainOptions(String[] data, LocalDateTime lastChange) {
        if (data[1].startsWith("casa:")) {
            houseOptions(data, lastChange);
        }
        else if (data[1].startsWith("fornecedor:")) {
            energySupplierOptions(data);
        }
    }

    /**
     * Analisa o terceiro elemento de uma linha separada por vírgulas considerando que a decisão do segundo elemento
     * foi uma casa
     *
     * @param data a linha
     * @param lastChange a data da ultima mudança que ocorreu durante a simulação automática
     */

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

    /**
     * Analisa o terceiro elemento de uma linha separada por vírgulas considerando que a decisão do segundo elemento
     * foi um dispositivo
     *
     * @param data a linha
     * @param factoryCode o código de fábrica do dispositivo
     * @param lastChange a data da ultima mudança que ocorreu durante a simulação automática
     */

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

    /**
     * Analisa o terceiro elemento de uma linha separada por vírgulas considerando que a decisão do segundo elemento
     * foi um fornecedor de energia
     *
     * @param data a linha
     */

    private void energySupplierOptions(String[] data) {
        if (data[2].equals("alteraValorDesconto")) {
            community.getEnergySuppliers().updateEnergySupplierFormula(data[1].substring(11), data[3]);
        }
    }

    /**
     * Remove e Soma todas as faturas que têm o mesmo endereço de casa e guarda a informação numa nova fatura
     */

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
