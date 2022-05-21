package com.grupo11.simulation;
import com.grupo11.energysupplier.EnergySupplier;
import com.grupo11.energysupplier.exception.EnergySupplierAlreadyExists;
import com.grupo11.main.MainModel;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smartdevice.SmartDeviceBulb;
import com.grupo11.smartdevice.SmartDeviceCamera;
import com.grupo11.smartdevice.SmartDeviceSpeaker;
import com.grupo11.smartdevice.exception.DeviceAlreadyExist;
import com.grupo11.smarthouse.Owner;
import com.grupo11.smarthouse.exception.HouseAddressAlreadyExists;
import com.grupo11.smarthouse.exception.HouseNotFound;
import com.grupo11.smarthouse.exception.RoomAlreadyExists;
import com.grupo11.smarthouse.exception.RoomNotFound;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseLogs {
    private MainModel community;

    private ParseLogs() {
    }

    public ParseLogs(MainModel community) {
        this.community = community;
    }

    /**
     * faz a leitura do arquivo de logs e consoante aquilo que está escrito adiciona as entidades ao programa
     *
     * @param filePath o caminho do arquivo de logs
     * @throws FileNotFoundException
     */

    public void parseLogs(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String houseAddress = null;
        String room = null;
        int smartDevices = 0;
        int smartHouses = 0;
        String factoryCode = String.valueOf(smartDevices);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(":");
            switch (data[0]) {
                case "Fornecedor" -> parseSupplier(data[1]);
                case "Casa" -> {
                    houseAddress = parseHouse(data[1], String.valueOf(smartHouses));
                    smartHouses++;
                }
                case "Divisao" -> room = parseRoom(houseAddress, data[1]);
                case "SmartBulb" -> {
                    parseSmartBulb(houseAddress, room, factoryCode, data[1]);
                    smartDevices++;
                    factoryCode = String.valueOf(smartDevices);
                }
                case "SmartSpeaker" -> {
                    parseSmartSpeaker(houseAddress, room, factoryCode, data[1]);
                    smartDevices++;
                    factoryCode = String.valueOf(smartDevices);
                }
                case "SmartCamera" -> {
                    parseSmartCamera(houseAddress, room, factoryCode, data[1]);
                    smartDevices++;
                    factoryCode = String.valueOf(smartDevices);
                }
            }
        }
    }

    /**
     * Analisa uma linha que corresponde a uma casa
     *
     * @param data a linha
     * @param address o endereço da suposta casa
     * @return o endereço da casa, ou null, caso não seja válido o input
     */

    private String parseHouse(String data, String address) {
        String[] houseData = data.split(",");
        try {
            community.getSmartHouses().addSmartHouse(address);
            Owner owner = new Owner(houseData[1], houseData[0]);
            community.getSmartHouses().updateOwner(address, owner);
            community.getSmartHouses().updateEnergySupplier(community.getEnergySuppliers(), address, houseData[2]);
        } catch (HouseAddressAlreadyExists | HouseNotFound e) {
            System.out.println(e.getMessage());
            address = null;
        }
        return address;
    }

    /**
     * Analisa uma linha que corresponde uma divisão
     *
     * @param houseAddress o endereço da casa
     * @param room a divisão da casa
     * @return a divisão da casa, ou null, caso não seja válido o input
     */

    private String parseRoom(String houseAddress, String room) {
        try {
            community.getSmartHouses().addRoomToHouse(houseAddress, room);
        } catch (HouseNotFound | RoomAlreadyExists e) {
            System.out.println(e.getMessage());
            room = null;
        }
        return room;
    }

    /**
     * Analisa uma linha correspondente a um fornecedor de Energia
     *
     * @param data a linha
     */

    private void parseSupplier(String data) {
        String[] supplierData = data.split(",");
        try {
            community.getEnergySuppliers().addEnergySupplier(supplierData[0], supplierData[1]);
        } catch (EnergySupplierAlreadyExists e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Analisa uma linha correspondente a um dispositivo do tipo SmartDeviceBulb
     *
     * @param address o endereço da casa onde vamos adicionar o dispositivo
     * @param room a divisão da casa onde vamos adicionar o dispositivo
     * @param factoryCode o código de fábrica do dispositivo
     * @param data a linha
     */

    private void parseSmartBulb(String address, String room, String factoryCode, String data) {
        String[] bulbData = data.split(",");
        SmartDeviceBulb.Tone tone = null;
        switch (bulbData[0]) {
            case "Warm" -> tone = SmartDeviceBulb.Tone.Warm;
            case "Neutral" -> tone = SmartDeviceBulb.Tone.Neutral;
            case "Cold" -> tone = SmartDeviceBulb.Tone.Cold;
        }
        SmartDeviceBulb smartBulb = new SmartDeviceBulb(tone, Double.parseDouble(bulbData[1]), SmartDevice.State.OFF,
                                           20, factoryCode, Double.parseDouble(bulbData[2]));
        try {
            community.getFactory().getSmartDeviceRepository().addSmartDevice(factoryCode, smartBulb);
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartBulb);
            community.getFactory().setDeviceAvailability(factoryCode, false);
        } catch (HouseNotFound | RoomNotFound | DeviceAlreadyExist e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Analisa uma linha correspondente a um dispositivo do tipo SmartDeviceSpeaker
     *
     * @param address o endereço da casa onde vamos adicionar o dispositivo
     * @param room a divisão da casa onde vamos adicionar o dispositivo
     * @param factoryCode o código de fábrica do dispositivo
     * @param data a linha
     */

    private void parseSmartSpeaker(String address, String room, String factoryCode, String data) {
        String[] speakerData = data.split(",");
        SmartDeviceSpeaker smartSpeaker = new SmartDeviceSpeaker(factoryCode, speakerData[1], speakerData[2],
                20, Double.parseDouble(speakerData[3]), 100, Integer.parseInt(speakerData[0]));
        try {
            community.getFactory().getSmartDeviceRepository().addSmartDevice(factoryCode, smartSpeaker);
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartSpeaker);
            community.getFactory().setDeviceAvailability(factoryCode, false);
        } catch (HouseNotFound | RoomNotFound | DeviceAlreadyExist e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Analisa uma linha correspondente a um dispositivo do tipo SmartDeviceCamera
     *
     * @param address o endereço da casa onde vamos adicionar o dispositivo
     * @param room a divisão da casa onde vamos adicionar o dispositivo
     * @param factoryCode o código de fábrica do dispositivo
     * @param data a linha
     */

    private void parseSmartCamera(String address, String room, String factoryCode, String data) {
        String[] cameraData = data.split(",");
        String[] parseResolution = cameraData[0].split("\\)");
        String[] parseResolution2 = parseResolution[0].split("x");
        int width = Integer.parseInt(parseResolution2[0].substring(1));
        int height = Integer.parseInt(parseResolution2[1]);
        int resolution = width * height;
        SmartDeviceCamera smartCamera = new SmartDeviceCamera(factoryCode, SmartDevice.State.OFF, 20,
                Double.parseDouble(cameraData[2]), Integer.parseInt(cameraData[1]), resolution);
        try {
            community.getFactory().getSmartDeviceRepository().addSmartDevice(factoryCode, smartCamera);
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartCamera);
            community.getFactory().setDeviceAvailability(factoryCode, false);
        } catch (HouseNotFound | RoomNotFound | DeviceAlreadyExist e) {
            System.out.println(e.getMessage());
        }
    }
}
