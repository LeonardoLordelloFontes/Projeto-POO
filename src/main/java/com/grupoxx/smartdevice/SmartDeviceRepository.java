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

    public void SmartDeviceBulbUpdade(String OldFactorycode, String NewFactorycode, String energyConsumption, String installationCost, String dimension){
        //Partindo do principio que os input são válidos

        SmartDeviceBulb sb = (SmartDeviceBulb) this.factory.get(OldFactorycode);
        if(!NewFactorycode.equals("#")) sb.setFactoryCode(NewFactorycode);
        if(!energyConsumption.equals("#")) sb.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) sb.setInstallationCost( Double.parseDouble(installationCost) );
        if(!dimension.equals("#")) sb.setDimension(Double.parseDouble (dimension) );

    }

    public void SmartDeviceSpeakerUpdate(String OldFactorycode, String NewFactorycode,String energyConsumption,String installationCost,String brand, String volumeMax){

        SmartDeviceSpeaker ss = (SmartDeviceSpeaker) this.factory.get(OldFactorycode);
        if(!NewFactorycode.equals("#")) ss.setFactoryCode(NewFactorycode);
        if(!energyConsumption.equals("#")) ss.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) ss.setInstallationCost( Double.parseDouble(installationCost) );
        if (!brand.equals("#")) ss.setBrand(brand);
        if (!volumeMax.equals("#")) ss.setVolumeMax(Integer.parseInt(volumeMax));
    }

    public void SmartDeviceCameraUpdate(String OldFactorycode, String NewFactorycode,String energyConsumption,String installationCost,String resolution ,String fileSize){
        SmartDeviceCamera sc = (SmartDeviceCamera) this.factory.get(OldFactorycode);

        if(!NewFactorycode.equals("#")) sc.setFactoryCode(NewFactorycode);
        if(!energyConsumption.equals("#")) sc.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) sc.setInstallationCost( Double.parseDouble(installationCost) );
        if(!resolution.equals("#")) sc.setResolution(Integer.parseInt(resolution));
        if(!resolution.equals("#")) sc.setResolution(Integer.parseInt(fileSize));
    }

    public Map<String, SmartDevice> getFactory() { return factory;}
}
