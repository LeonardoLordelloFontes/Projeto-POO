package com.grupoxx;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smarthouse.SmartHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, SmartHouse> smartHouses = new HashMap<>(); // House Address -> SmartHouse
        Map<SmartDevice, String> smartDevices = new HashMap<>(); // SmartDevice -> House Address
        Map<EnergySupplier, List<String>> energySuppliers = new HashMap<>(); // EnergySupplier -> House Address
    }
}

