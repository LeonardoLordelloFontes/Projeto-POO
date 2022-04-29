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
    /**
     * Local onde vamos depositar dispositivos
     */
    private Map<String,SmartDevice> factory = new HashMap<>();

    /**
     * Construtor de SmartDeviceRepository
     */
    public SmartDeviceRepository() {
        this.factory = new HashMap<>();
    }

    /**
     * Metodo da casa
     * Metodo que encontra um dispositivo se ele exixtir pelo seu código de fábrica
     *
     * @param factoryCode do dispositivo que queremos encontrar
     * @return o dispositivo que tem o código de fabrica dado
     * @throws DeviceNotFound se um dispositivo não existir
     */
    public SmartDevice findSmartDeviceByFactoryCode (String factoryCode) throws DeviceNotFound {
        SmartDevice sd = this.factory.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");
        return sd;
    }

    /**
     * Metodo da casa
     * Metodo que adiciona ao repositório um dispositivo genéricamnete
     *
     * @param factoryCode código de fábrica do dispositivo
     * @param smartDevice o dispositivo em concreto
     * @throws DeviceAlreadyExist se o dispositivo já existir
     */
    public void addSmartDevice(String factoryCode, SmartDevice smartDevice) throws DeviceAlreadyExist {
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O dispositivo de código de fábrica "+factoryCode+"já existe!!");

        factory.put(factoryCode, smartDevice);

    }

    /**
     * Metodo da fabrica
     * Metodo que cria lâmpadas e as adiciona ao repositório
     *
     * @param factoryCode da lâmpada a criar
     * @param installationCost da lâmpada a criar
     * @param energyConsumption da lâmpada a criar
     * @param dimension da lâmpada a criar
     * @throws DeviceAlreadyExist se a lâmpada já existir
     */
    public void SmartDeviceBulbAdd(String factoryCode,  double installationCost, double energyConsumption, double dimension ) throws DeviceAlreadyExist{
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartBulb de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceBulb sb = new SmartDeviceBulb(factoryCode, dimension, installationCost, energyConsumption);
        this.factory.put(factoryCode, sb);

    }

    /**
     * Metodo da fábrica
     * Metodo que cria colunas e as adiciona ao repositório
     *
     * @param factoryCode da coluna a criar
     * @param installationCost da coluna a criar
     * @param energyConsumption da coluna a criar
     * @param brand da coluna a criar
     * @param volumeMax da coluna a criar
     * @throws DeviceAlreadyExist se a coluna já existir
     */
    public void SmartDeviceSpeakerAdd(String factoryCode,double installationCost, double energyConsumption,String brand, int volumeMax)throws DeviceAlreadyExist{
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartSpeaker de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceSpeaker ss = new SmartDeviceSpeaker(factoryCode, installationCost, energyConsumption, brand, volumeMax);
        this.factory.put(factoryCode, ss);

    }

    /**
     * Metodo da fábrica
     * Metodo que cria colunas e as adiciona ao repositório
     *
     * @param factoryCode da camera a criar
     * @param installationCost da camera a criar
     * @param energyConsumption da camera a criar
     * @param resolution da camera a criar
     * @param fileSize da camera a criar
     * @throws DeviceAlreadyExist se a camara já existir
     */
    public void SmartDeviceCameraAdd(String factoryCode,double installationCost, double energyConsumption,int resolution ,int fileSize)throws DeviceAlreadyExist{
        if(this.factory.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartCamera de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceCamera sc = new SmartDeviceCamera(factoryCode, installationCost, energyConsumption,resolution,fileSize);
        this.factory.put(factoryCode, sc);


    }

    /**
     * Metodo da fabrica
     * Metodo que remove dispositivos da fábrica
     *
     * @param factoryCode do dispositivo a remover
     * @throws DeviceNotFound se o dispositivo não existir
     */
    public void SmartDeviceRemove(String factoryCode) throws DeviceNotFound {
        SmartDevice sd = this.factory.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ "não foi encontrado!!");

        this.factory.remove(factoryCode);
    }

    /**
     * Metodo de fabrica
     * Metodo que atualiza lâmpadas
     *
     * @param oldFactoryCode da lâmpada a atualizar
     * @param newFactoryCode da lâmpada a atualizar
     * @param energyConsumption da lâmpada a atualizar
     * @param installationCost da lâmpada a atualizar
     * @param dimension da lâmpada a atualizar
     * @param tone da lâmpada a atualizar
     * @throws DeviceNotFound se a lâmpada não existir
     */
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

    /**
     * Metodo da fabrica
     * Metodo que atualiza colunas
     *
     * @param oldFactoryCode da coluna a atualizar
     * @param newFactoryCode da coluna a atualizar
     * @param energyConsumption da coluna a atualizar
     * @param installationCost da coluna a atualizar
     * @param brand da coluna a atualizar
     * @param volumeMax da coluna a atualizar
     * @param volume da coluna a atualizar
     * @param radioStation da coluna a atualizar
     * @throws DeviceNotFound se a coluna não existir
     */
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

    /**
     * Metodo de fabrica
     * Metodo que atualiza camaras
     *
     * @param oldFactoryCode da camara a atualizar
     * @param newFactoryCode da camara a atualizar
     * @param energyConsumption da camara a atualizar
     * @param installationCost da camara a atualizar
     * @param resolution da camara a atualizar
     * @param fileSize da camara a atualizar
     * @throws DeviceNotFound se a camara não existir
     */
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
