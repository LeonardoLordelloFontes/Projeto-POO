package com.grupoxx.smartdevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartDeviceRepository {

    private Map<String,SmartDevice> factory = new HashMap<>();

    public SmartDevice getDevice(String factoryCode){
        return this.factory.get(factoryCode).clone();
    }


    public SmartDeviceRepository() {
        this.factory = new HashMap<>();
    }

    public SmartDevice findSmartDeviceByFactoryCode(String factoryCode) {
        // TODO, tratamento de exceções
        return factory.get(factoryCode);
    }

    public void addSmartDevice(String factoryCode, SmartDevice smartDevice) {
        // TODO, tratamento de exceções
        factory.put(factoryCode, smartDevice);
    }

    public boolean SmartDeviceBulbAdd(String factorycode,  double installationCost, double energyConsumption, double dimension ){

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

    public void SmartDeviceBulbUpdade(String oldFactorycode, String newFactorycode, String energyConsumption, String installationCost, String dimension){
        //Partindo do principio que os input são válidos

        SmartDeviceBulb sb = (SmartDeviceBulb) this.factory.get(oldFactorycode);
        if(!newFactorycode.equals("#")){
            sb.setFactoryCode(newFactorycode);
            this.factory.put(newFactorycode,sb);
            this.factory.remove(oldFactorycode);
        }
        if(!energyConsumption.equals("#")) sb.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) sb.setInstallationCost( Double.parseDouble(installationCost) );
        if(!dimension.equals("#")) sb.setDimension(Double.parseDouble (dimension) );

    }

    public void SmartDeviceSpeakerUpdate(String oldFactorycode, String newFactorycode,String energyConsumption,String installationCost,String brand, String volumeMax){

        SmartDeviceSpeaker ss = (SmartDeviceSpeaker) this.factory.get(oldFactorycode);
        if(!newFactorycode.equals("#")){
            ss.setFactoryCode(newFactorycode);
            this.factory.put(newFactorycode,ss);
            this.factory.remove(oldFactorycode);
        }
        if(!energyConsumption.equals("#")) ss.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) ss.setInstallationCost( Double.parseDouble(installationCost) );
        if (!brand.equals("#")) ss.setBrand(brand);
        if (!volumeMax.equals("#")) ss.setVolumeMax(Integer.parseInt(volumeMax));
    }

    public void SmartDeviceCameraUpdate(String oldFactorycode, String newFactorycode,String energyConsumption,String installationCost,String resolution ,String fileSize){
        SmartDeviceCamera sc = (SmartDeviceCamera) this.factory.get(oldFactorycode);

        if(!newFactorycode.equals("#")){
            sc.setFactoryCode(newFactorycode);
            this.factory.put(newFactorycode,sc);
            this.factory.remove(oldFactorycode);
        }
        if(!energyConsumption.equals("#")) sc.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) sc.setInstallationCost( Double.parseDouble(installationCost) );
        if(!resolution.equals("#")) sc.setResolution(Integer.parseInt(resolution));
        if(!resolution.equals("#")) sc.setResolution(Integer.parseInt(fileSize));
    }

    public void SmartDeviceState(String deviceToTurn, SmartDevice.State turn){

        if(deviceToTurn.equals("sb")){
                for(SmartDevice sb: this.factory.values())
                    if (sb instanceof SmartDeviceBulb) sb.setState(turn);}

        if(deviceToTurn.equals("ss")){
                for(SmartDevice ss: this.factory.values())
                    if (ss instanceof SmartDeviceSpeaker) ss.setState(turn);}

        if(deviceToTurn.equals("sc")){
                for(SmartDevice sc: this.factory.values())
                    if (sc instanceof SmartDeviceCamera) sc.setState(turn);}

        else{
            for(SmartDevice sd: this.factory.values())
                    sd.setState(turn);}
    }

    public void SmartEpecificDiviceState(String factoryCode, SmartDevice.State turn){

        this.factory.get(factoryCode).setState(turn);

    }

    public void SmartDeviceTone(String tone){

        if (tone.equals("N"))
            for(SmartDevice sb: this.factory.values())
                if (sb instanceof SmartDeviceBulb) ((SmartDeviceBulb) sb).setTone(SmartDeviceBulb.Tone.Neutral);

        if(tone.equals("W") )
            for(SmartDevice sb: this.factory.values())
                if (sb instanceof SmartDeviceBulb) ((SmartDeviceBulb) sb).setTone(SmartDeviceBulb.Tone.Warm);

        if(tone.equals("C") )
            for(SmartDevice sb: this.factory.values())
                if (sb instanceof SmartDeviceBulb) ((SmartDeviceBulb) sb).setTone(SmartDeviceBulb.Tone.Cold);

    }

    public void SmartDeviceEspecificTone(String factoryCode,String tone){
        SmartDevice sb = this.factory.get(factoryCode);
        if( sb instanceof SmartDeviceBulb){

            if (tone.equals("N")) ((SmartDeviceBulb) sb).setTone(SmartDeviceBulb.Tone.Neutral);

            if (tone.equals("W")) ((SmartDeviceBulb) sb).setTone(SmartDeviceBulb.Tone.Warm);

            if (tone.equals("C")) ((SmartDeviceBulb) sb).setTone(SmartDeviceBulb.Tone.Cold);
        }
    }

    public void SmartDeviceSpeakerProperties(String propertie,String change){

        if( change.equals("V") ){
            int volume = Integer.parseInt(propertie);
            for(SmartDevice ss: this.factory.values())
                if (ss instanceof SmartDeviceSpeaker) ((SmartDeviceSpeaker) ss).setVolume(volume);
        }

        if (change.equals("R"))
            for(SmartDevice ss: this.factory.values())
                if (ss instanceof SmartDeviceSpeaker) ((SmartDeviceSpeaker) ss).setRadio(propertie);


    }

    public void SmartDeviceEspecificSmartSpeakerProperties(String factoryCode, String volume, String radioStation){
        SmartDevice ss = this.factory.get(factoryCode);
        if( ss instanceof SmartDeviceSpeaker) {
            if (!volume.equals("#")) ((SmartDeviceSpeaker) ss).setVolume(Integer.parseInt(volume));
            if (!radioStation.equals("#")) ((SmartDeviceSpeaker) ss).setRadio(radioStation);
        }

    }


    public List<SmartDevice> findAllSmartDevices() {
        return new ArrayList<>(factory.values());
    }

    public Map<String, SmartDevice> getFactory() { return factory;}

}
