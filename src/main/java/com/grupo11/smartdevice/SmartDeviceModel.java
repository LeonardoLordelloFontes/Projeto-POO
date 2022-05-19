package com.grupo11.smartdevice;

import com.grupo11.smartdevice.exception.DeviceAlreadyExist;
import com.grupo11.smartdevice.exception.DeviceNotFound;
import java.util.function.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartDeviceModel implements Serializable {
    /**
     * Local onde vamos depositar dispositivos
     */
    private Map<String,SmartDevice> storage = new HashMap<>();

    /**
     * Construtor de SmartDeviceRepository
     */
    public SmartDeviceModel() {
        this.storage = new HashMap<>();
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
        SmartDevice sd = this.storage.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ " não foi encontrado!!");
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
        if(this.storage.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O dispositivo de código de fábrica "+factoryCode+" já existe!!");

        storage.put(factoryCode, smartDevice);
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
        if(this.storage.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartBulb de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceBulb sb = new SmartDeviceBulb(factoryCode, dimension, installationCost, energyConsumption);
        this.storage.put(factoryCode, sb);

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
        if(this.storage.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartSpeaker de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceSpeaker ss = new SmartDeviceSpeaker(factoryCode, installationCost, energyConsumption, brand, volumeMax);
        this.storage.put(factoryCode, ss);

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
        if(this.storage.get(factoryCode) != null)
            throw new DeviceAlreadyExist("O  SmartCamera de código de fábrica "+factoryCode+"já existe!!");

        SmartDeviceCamera sc = new SmartDeviceCamera(factoryCode, installationCost, energyConsumption,resolution,fileSize);
        this.storage.put(factoryCode, sc);


    }

    /**
     * Metodo da fabrica
     * Metodo que remove dispositivos da fábrica
     *
     * @param factoryCode do dispositivo a remover
     * @throws DeviceNotFound se o dispositivo não existir
     */
    public void SmartDeviceRemove(String factoryCode) throws DeviceNotFound{
        SmartDevice sd = this.storage.get(factoryCode);
        if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ " não foi encontrado!!");

        this.storage.remove(factoryCode);
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
    public void SmartDeviceBulbUpdade(String oldFactoryCode, String newFactoryCode, String energyConsumption, String installationCost, String dimension, String tone, String keep)throws DeviceNotFound {
        //Partindo do principio que os input são válidos

        SmartDeviceBulb sb = (SmartDeviceBulb) this.storage.get(oldFactoryCode);
        if(sb == null) throw new DeviceNotFound("O SmartBulb de código de fábrica "+oldFactoryCode+ "não foi encontrado!!");

        if(!newFactoryCode.equals(keep)){
            sb.setFactoryCode(newFactoryCode);
            this.storage.put(newFactoryCode,sb);
            this.storage.remove(oldFactoryCode);
        }
        if(!energyConsumption.equals(keep)) sb.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals(keep)) sb.setInstallationCost( Double.parseDouble(installationCost) );
        if(!dimension.equals(keep)) sb.setDimension(Double.parseDouble (dimension) );
        if(!tone.equals(keep)){

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
    public void SmartDeviceSpeakerUpdate(String oldFactoryCode, String newFactoryCode,String energyConsumption,String installationCost,String brand, String volumeMax, String volume, String radioStation,String keep)throws DeviceNotFound {

        SmartDeviceSpeaker ss = (SmartDeviceSpeaker) this.storage.get(oldFactoryCode);
        if(ss == null) throw new DeviceNotFound("O SmartSpeaker de código de fábrica "+oldFactoryCode+ "não foi encontrado!!");

        if(!newFactoryCode.equals(keep)){
            ss.setFactoryCode(newFactoryCode);
            this.storage.put(newFactoryCode,ss);
            this.storage.remove(oldFactoryCode);
        }
        if(!energyConsumption.equals(keep)) ss.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals(keep)) ss.setInstallationCost( Double.parseDouble(installationCost) );
        if (!brand.equals(keep)) ss.setBrand(brand);
        if (!volumeMax.equals(keep)) ss.setVolumeMax(Integer.parseInt(volumeMax));
        if (!volume.equals(keep)) ss.setVolume(Integer.parseInt(volume));
        if (!radioStation.equals(keep)) ss.setRadio(radioStation);

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
    public void SmartDeviceCameraUpdate(String oldFactoryCode, String newFactoryCode,String energyConsumption,String installationCost,String resolution ,String fileSize, String keep)throws DeviceNotFound {

        SmartDeviceCamera sc = (SmartDeviceCamera) this.storage.get(oldFactoryCode);
        if(sc == null) throw new DeviceNotFound("O SmartCamera de código de fábrica "+oldFactoryCode+ "não foi encontrado!!");

        if(!newFactoryCode.equals(keep)) {
            sc.setFactoryCode(newFactoryCode);
            this.storage.put(newFactoryCode,sc);
            this.storage.remove(oldFactoryCode);
        }
        if(!energyConsumption.equals(keep)) sc.setEnergyConsumption( Double.parseDouble(energyConsumption) );
        if(!installationCost.equals(keep)) sc.setInstallationCost( Double.parseDouble(installationCost) );
        if(!resolution.equals(keep)) sc.setResolution(Integer.parseInt(resolution));
        if(!resolution.equals(keep)) sc.setFileSize(Integer.parseInt(fileSize));
    }

    /**
     * Metodo da casa
     * Metodo que liga e desliga todos os dispositivos
     * @param p é o predicado que verifica se é para ligar ou desligar
     * @param turn é o estado que os divixe têm de ser alterados
     *
     */

    public void smartDeviceState(Predicate<SmartDevice> p, SmartDevice.State turn) {
        for(SmartDevice sb: this.storage.values())
            if  (p.test(sb)) sb.setState(turn);
    }

    /**
     * Metodo da casa
     * Metodo que seleciona a tonalidade de todos os dispositivos
     * @param tone é a tonalidade que as lampâdas devem ser alteradas
     *
     */
    public void smartDeviceTone(SmartDeviceBulb.Tone tone){
        Predicate<SmartDevice> p = x-> x instanceof SmartDeviceBulb;
        for(SmartDevice sb: this.storage.values())
            if (p.test(sb)) ((SmartDeviceBulb) sb).setTone(tone);
    }

    /**
     * Metodo da casa e fabrica
     *
     * @return uma a lista dos dispositivos que estão na fábrica
     */

    public List<SmartDevice> findAllSmartDevices() {
        return new ArrayList<>(storage.values());
    }

    /**
     * Metodo da casa e fabrica
     *
     * @return um repositório de dos dispositivos
     */

    public Map<String, SmartDevice> getStorage() { return storage;}
}
