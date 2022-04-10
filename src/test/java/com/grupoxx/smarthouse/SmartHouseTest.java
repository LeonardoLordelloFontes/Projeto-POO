package com.grupoxx.smarthouse;

import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceBulb;
import com.grupoxx.smartdevice.SmartDeviceCamera;
import com.grupoxx.smartdevice.SmartDeviceSpeaker;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SmartHouseTest {
    @Test
    void itShouldAddSmartDevicesAndVerifySize() {
        Owner owner = new Owner();
        List<String> listRooms = new ArrayList<>();
        Collections.addAll(listRooms, "Sala", "Casa de Banho", "Cozinha");
        SmartHouse smartHouse = new SmartHouse(owner, "ADRESS", listRooms);
        SmartDevice smartBulb = new SmartDeviceBulb("1234");
        SmartDevice smartCamera = new SmartDeviceCamera("2345");
        SmartDevice smartSpeaker = new SmartDeviceSpeaker("5678");
        smartHouse.addSmartDevice(smartBulb, "Sala");
        smartHouse.addSmartDevice(smartCamera, "Sala");
        smartHouse.addSmartDevice(smartSpeaker, "Sala");
        Map<String, SmartDevice> smartDevices = smartHouse.getSmartDevices();
        Map<String, List<String>> rooms = smartHouse.getRooms();
        assertEquals(3, rooms.get("Sala").size());
        assertEquals(0, rooms.get("Casa de Banho").size());
        assertEquals(0, rooms.get("Cozinha").size());
        assertEquals(3, smartDevices.size());
    }

    @Test
    void itShouldAddSmartDevicesAndVerifyItsStorage() {
        Owner owner = new Owner();
        List<String> listRooms = new ArrayList<>();
        Collections.addAll(listRooms, "Sala", "Casa de Banho", "Cozinha");
        SmartHouse smartHouse = new SmartHouse(owner, "ADRESS", listRooms);
        SmartDevice smartBulb = new SmartDeviceBulb("1234");
        SmartDevice smartCamera = new SmartDeviceCamera("2345");
        SmartDevice smartSpeaker = new SmartDeviceSpeaker("5678");
        smartHouse.addSmartDevice(smartBulb, "Sala");
        smartHouse.addSmartDevice(smartCamera, "Sala");
        smartHouse.addSmartDevice(smartSpeaker, "Sala");
        Map<String, SmartDevice> smartDevices = smartHouse.getSmartDevices();
        Map<String, List<String>> rooms = smartHouse.getRooms();
        // TODO
    }

    @Test
    void itShouldAddRoomsAndVerifySize() {
        SmartHouse smartHouse = new SmartHouse();
        smartHouse.addRoom("Sala");
        smartHouse.addRoom("Casa de Banho");
        smartHouse.addRoom("Cozinha");
        Map<String, List<String>> rooms = smartHouse.getRooms();
        assertEquals(3, rooms.size());
    }

    @Test
    void itShouldAddRoomsAndVerifyItsStorage() {
        SmartHouse smartHouse = new SmartHouse();
        smartHouse.addRoom("Sala");
        smartHouse.addRoom("Casa de Banho");
        smartHouse.addRoom("Cozinha");
        Map<String, List<String>> rooms = smartHouse.getRooms();
        assertTrue(rooms.containsKey("Sala") && rooms.get("Sala") != null);
        assertTrue(rooms.containsKey("Casa de Banho") && rooms.get("Casa de Banho") != null);
        assertTrue(rooms.containsKey("Cozinha") && rooms.get("Cozinha") != null);
    }
}