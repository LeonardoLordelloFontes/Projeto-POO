package com.grupoxx.factory;

import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smartdevice.exception.DeviceNotFoud;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Factory implements Serializable {
    SmartDeviceRepository smartDeviceRepository;
    Map<String, Boolean> available;
    String address; // Para este trabalho não nos interessa, mas poderiamos utilizar isto para criar várias fábricas

    public Factory() {
        this.smartDeviceRepository = new SmartDeviceRepository();
        this.available = new HashMap<>();
        this.address = "FACTORY ADDRESS";
    }

    public SmartDeviceRepository getSmartDeviceRepository() {
        return this.smartDeviceRepository;
    }

    public void setSmartDeviceRepository(SmartDeviceRepository smartDeviceRepository) {
        this.smartDeviceRepository = smartDeviceRepository;
    }

    public void deleteDevice(String factoryCode)throws DeviceNotFoud {
        if (this.available.get(factoryCode) == null) throw new DeviceNotFoud("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");
        this.available.remove(factoryCode);
    }

    public void setDeviceAvailability(String factoryCode, Boolean available)throws DeviceNotFoud {
        if (this.available.get(factoryCode) == null) throw new DeviceNotFoud("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");
        this.available.put(factoryCode, available);
    }

    public boolean isDeviceAvailable(String factoryCode)throws DeviceNotFoud {
        if (this.available.get(factoryCode) == null) throw new DeviceNotFoud("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");

        return available.get(factoryCode);
    }


}
