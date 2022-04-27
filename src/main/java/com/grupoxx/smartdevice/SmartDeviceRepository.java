package com.grupoxx.smartdevice;

import com.grupoxx.smartdevice.exception.DeviceAlreadyExist;
import com.grupoxx.smartdevice.exception.DeviceNotFound;
import java.util.function.Predicate;
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

    public void SmartDeviceBulbUpdade(String oldFactoryCode, String newFactoryCode, String energyConsumption, String installationCost, String dimension, String tone)throws DeviceNotFound {
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
        if(!tone.equals("#")){

                if (tone.equals("1")) sb.setTone(SmartDeviceBulb.Tone.Neutral);

                if (tone.equals("2")) sb.setTone(SmartDeviceBulb.Tone.Warm);

                if (tone.equals("3")) sb.setTone(SmartDeviceBulb.Tone.Cold);

            }
        }

    public void SmartDeviceSpeakerUpdate(String oldFactoryCode, String newFactoryCode,String energyConsumption,String installationCost,String brand, String volumeMax, String volume, String radioStation)throws DeviceNotFound {

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
        if (!volume.equals("#")) ss.setVolume(Integer.parseInt(volume));
        if (!radioStation.equals("#")) ss.setRadio(radioStation);

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
        if(!resolution.equals("#")) sc.setFileSize(Integer.parseInt(fileSize));
    }

    public void SmartDeviceState(Predicate p, SmartDevice.State turn){

        for(SmartDevice sb: this.factory.values())
            if  ( p.test(sb) ) sb.setState(turn);

    }

    public void SmartDeviceTone(SmartDeviceBulb.Tone tone){
        Predicate p = x-> x instanceof SmartDeviceBulb;
        for(SmartDevice sb: this.factory.values())
            if (p.test(sb)) ((SmartDeviceBulb) sb).setTone(tone);
    }

    public List<SmartDevice> findAllSmartDevices() {

        return new ArrayList<>(factory.values());
    }

    public Map<String, SmartDevice> getFactory() { return factory;}
}
