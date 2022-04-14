package com.grupoxx.smartdevice;

import java.util.HashMap;
import java.util.Map;

public class SmartDeviceRepository {
    private Map<String,SmartDevice> factory = new HashMap<>();


    public SmartDeviceRepository() {

        this.factory = new HashMap<>();
    }

    public boolean SmartDeviceBulbAdd(String factorycode, double dimension, double installationCost, double energyConsumption){

        try {
            SmartDeviceBulb sb = new SmartDeviceBulb(factorycode, dimension, installationCost, energyConsumption);
            this.factory.put(factorycode, sb);
            return true;}

        catch (Exception e){
            // Caso o codigo de fabrica já exista.
            return false;}

    }

    public boolean SmartDeviceSpeakerAdd(String factorycode,double installationCost, double energyConsumption,String brand, int volumeMax){
        try {
            SmartDeviceSpeaker ss = new SmartDeviceSpeaker(factorycode, installationCost, energyConsumption, brand, volumeMax);
            this.factory.put(factorycode, ss);
            return true;}

        catch (Exception e){
            // Caso o codigo de fabrica já exista.
            return false;}
    }

    public boolean SmartDeviceCameraAdd(String factorycode,double installationCost, double energyConsumption,int resolution ,int fileSize){
        try {
            SmartDeviceCamera sc = new SmartDeviceCamera(factorycode, installationCost, energyConsumption,resolution,fileSize);
            this.factory.put(factorycode, sc);
            return true;}

        catch (Exception e){
            // Caso o codigo de fabrica já exista.
            return false;}
    }

    public boolean SmartDeviceRemove(String factorycode){
        try {
            this.factory.remove(factorycode);
            return true;
        }
        catch (Exception e){
            //Caso o codigo de frabrica não exista
            return false;
        }
    }

    public Map<String, SmartDevice> getFactory() { return factory;}
}
