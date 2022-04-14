package com.grupoxx.smartdevice;

import java.util.HashMap;
import java.util.Map;

public class SmartDeviceRepository {
    private Map<String,SmartDevice> factory = new HashMap<>();


    public SmartDeviceRepository() {

        this.factory = new HashMap<>();
    }


    public Map<String, SmartDevice> getFactory() { return factory;}
}
