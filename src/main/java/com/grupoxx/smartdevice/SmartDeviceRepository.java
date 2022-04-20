package com.grupoxx.smartdevice;

import com.grupoxx.smartdevice.exception.DeviceAlreadyExist;
import com.grupoxx.smartdevice.exception.DeviceNotFound;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartDeviceRepository implements Serializable {

    private Map<String,SmartDevice> factory = new HashMap<>();

    public SmartDeviceRepository() {
        this.factory = new HashMap<>();
    }

    public SmartDevice findSmartDeviceByFactoryCode (String factoryCode) throws DeviceNotFound {
        SmartDevice sd = this.factory.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");
        return sd;
    }

    public void addSmartDevice(String factoryCode, SmartDevice smartDevice) throws DeviceAlreadyExist {
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O dispositivo de código de fábrica "+factoryCode+"já existe!!");

        factory.put(factoryCode, smartDevice);

    }

    public void SmartDeviceBulbAdd(String factoryCode,  double installationCost, double energyConsumption, double dimension ) throws DeviceAlreadyExist{
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartBulb de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceBulb sb = new SmartDeviceBulb(factoryCode, dimension, installationCost, energyConsumption);
        this.factory.put(factoryCode, sb);


    }

    public void SmartDeviceSpeakerAdd(String factoryCode,double installationCost, double energyConsumption,String brand, int volumeMax)throws DeviceAlreadyExist{
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartSpeaker de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceSpeaker ss = new SmartDeviceSpeaker(factoryCode, installationCost, energyConsumption, brand, volumeMax);
        this.factory.put(factoryCode, ss);

    }

    public void SmartDeviceCameraAdd(String factoryCode,double installationCost, double energyConsumption,int resolution ,int fileSize)throws DeviceAlreadyExist{
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartCamera de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceCamera sc = new SmartDeviceCamera(factoryCode, installationCost, energyConsumption,resolution,fileSize);
        this.factory.put(factoryCode, sc);


    }

    public void SmartDeviceRemove(String factoryCode) throws DeviceNotFound {
        SmartDevice sd = this.factory.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");

        this.factory.remove(factoryCode);
    }

    public void SmartDeviceBulbUpdade(String oldFactoryCode, String newFactoryCode, String energyConsumption, String installationCost, String dimension)throws DeviceNotFound {
        //Partindo do principio que os input são válidos

        SmartDeviceBulb sb = (SmartDeviceBulb) this.factory.get(oldFactoryCode);
        if(sb == null) throw new DeviceNotFound("O SmartBulb de código de fábrica "+oldFactoryCode+ "não foi encontrado!!");

        if(!newFactoryCode.equals("#")){
            sb.setFactoryCode(newFactoryCode);
            this.factory.put(newFactoryCode,sb);
            this.factory.remove(oldFactoryCode);
        }
        if(!energyConsumption.equals("#")) sb.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) sb.setInstallationCost( Double.parseDouble(installationCost) );
        if(!dimension.equals("#")) sb.setDimension(Double.parseDouble (dimension) );

    }

    public void SmartDeviceSpeakerUpdate(String oldFactoryCode, String newFactoryCode,String energyConsumption,String installationCost,String brand, String volumeMax)throws DeviceNotFound {

        SmartDeviceSpeaker ss = (SmartDeviceSpeaker) this.factory.get(oldFactoryCode);
        if(ss == null) throw new DeviceNotFound("O SmartSpeaker de código de fábrica "+oldFactoryCode+ "não foi encontrado!!");

        if(!newFactoryCode.equals("#")){
            ss.setFactoryCode(newFactoryCode);
            this.factory.put(newFactoryCode,ss);
            this.factory.remove(oldFactoryCode);
        }
        if(!energyConsumption.equals("#")) ss.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals("#")) ss.setInstallationCost( Double.parseDouble(installationCost) );
        if (!brand.equals("#")) ss.setBrand(brand);
        if (!volumeMax.equals("#")) ss.setVolumeMax(Integer.parseInt(volumeMax));
    }

    public void SmartDeviceCameraUpdate(String oldFactoryCode, String newFactoryCode,String energyConsumption,String installationCost,String resolution ,String fileSize)throws DeviceNotFound {

        SmartDeviceCamera sc = (SmartDeviceCamera) this.factory.get(oldFactoryCode);
        if(sc == null) throw new DeviceNotFound("O SmartCamera de código de fábrica "+oldFactoryCode+ "não foi encontrado!!");

        if(!newFactoryCode.equals("#")){
            sc.setFactoryCode(newFactoryCode);
            this.factory.put(newFactoryCode,sc);
            this.factory.remove(oldFactoryCode);
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

    public void SmartEpecificDiviceState(String factoryCode, SmartDevice.State turn) throws DeviceNotFound {
        SmartDevice sd = this.factory.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");

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

    public void SmartDeviceEspecificTone(String factoryCode,String tone) throws DeviceNotFound {

        SmartDevice sb = this.factory.get(factoryCode);
        if(sb == null) throw new DeviceNotFound("O SmartBulb de código de fábrica "+factoryCode+ "não foi encontrado!!");

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

    public void SmartDeviceEspecificSmartSpeakerProperties(String factoryCode, String volume, String radioStation) throws DeviceNotFound {
        SmartDevice ss = this.factory.get(factoryCode);
        if(ss == null) throw new DeviceNotFound("O SmartSpeaker de código de fábrica "+factoryCode+ "não foi encontrado!!");

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
