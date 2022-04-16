package com.grupoxx.factory;

import com.grupoxx.smartdevice.SmartDeviceRepository;

import java.util.HashMap;
import java.util.Map;

public class Factory {
    SmartDeviceRepository smartDeviceRepository;
    Map<String, Boolean> available;
    String address; // Para este trabalho não nos interessa, mas poderiamos utilizar isto para criar várias fábricas

    public Factory() {
        this.smartDeviceRepository = new SmartDeviceRepository();
        this.available = new HashMap<>();
        this.address = "FACTORY ADDRESS";
    }
}
