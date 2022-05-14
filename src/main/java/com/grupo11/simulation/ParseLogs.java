package com.grupo11.simulation;
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

    public void parseLogs(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String houseAddress = null;
        String room = null;
        int i = 0;
        String factoryCode = String.valueOf(i);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(":");
            switch (data[0]) {
                case "Fornecedor" -> parseSupplier(data[1]);
                case "Casa" -> houseAddress = parseHouse(data[1]);
                case "Divisao" -> room = parseRoom(houseAddress, data[1]);
                case "SmartBulb" -> {
                    parseSmartBulb(houseAddress, room, factoryCode, data[1]);
                    i++;
                    factoryCode = String.valueOf(i);
                }
                case "SmartSpeaker" -> {
                    parseSmartSpeaker(houseAddress, room, factoryCode, data[1]);
                    i++;
                    factoryCode = String.valueOf(i);
                }
                case "SmartCamera" -> {
                    parseSmartCamera(houseAddress, room, factoryCode, data[1]);
                    i++;
                    factoryCode = String.valueOf(i);
                }
            }
        }
    }

    private String parseHouse(String data) {
        String[] houseData = data.split(",");
        String address = houseData[0];
        try {
            community.getSmartHouses().addSmartHouse(address);
            Owner owner = new Owner(houseData[1], houseData[2]);
            community.getSmartHouses().updateOwner(address, owner);
        } catch (HouseAddressAlreadyExists | HouseNotFound e) {
            System.out.println(e.getMessage());
            address = null;
        }
        return address;
    }

    private String parseRoom(String houseAddress, String room) {
        try {
            community.getSmartHouses().addRoomToHouse(houseAddress, room);
        } catch (HouseNotFound | RoomAlreadyExists e) {
            System.out.println(e.getMessage());
            room = null;
        }
        return room;
    }

    private void parseSupplier(String data) {
        String[] supplierData = data.split(",");
        try {
            community.getEnergySuppliers().addEnergySupplier(supplierData[0], supplierData[1]);
        } catch (EnergySupplierAlreadyExists e) {
            System.out.println(e.getMessage());
        }
    }

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
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartBulb);
            community.getFactory().setDeviceAvailability(factoryCode, false);
        } catch (HouseNotFound | RoomNotFound | DeviceAlreadyExist e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseSmartSpeaker(String address, String room, String factoryCode, String data) {
        String[] speakerData = data.split(",");
        SmartDeviceSpeaker smartSpeaker = new SmartDeviceSpeaker(factoryCode, speakerData[1], speakerData[2],
                20, Double.parseDouble(speakerData[3]), 100, Integer.parseInt(speakerData[0]));
        try {
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartSpeaker);
            community.getFactory().setDeviceAvailability(factoryCode, false);
        } catch (HouseNotFound | RoomNotFound | DeviceAlreadyExist e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseSmartCamera(String address, String room, String factoryCode, String data) {
        String[] cameraData = data.split(",");
        String[] parseResolution = data.split("x");
        int width = Integer.parseInt(parseResolution[0].substring(1));
        int height = Integer.parseInt(parseResolution[1].substring(0, parseResolution[1].length() - 2));
        int resolution = width * height;
        SmartDeviceCamera smartCamera = new SmartDeviceCamera(factoryCode, SmartDevice.State.OFF, 20,
                Double.parseDouble(cameraData[2]), Integer.parseInt(cameraData[1]), resolution);
        try {
            community.getSmartHouses().findSmartDevicesByRoom(address, room).addSmartDevice(factoryCode, smartCamera);
            community.getFactory().setDeviceAvailability(factoryCode, false);
        } catch (HouseNotFound | RoomNotFound | DeviceAlreadyExist e) {
            System.out.println(e.getMessage());
        }
    }
}
