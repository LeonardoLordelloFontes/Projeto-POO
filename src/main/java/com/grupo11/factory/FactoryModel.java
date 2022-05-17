package com.grupo11.factory;

import com.grupo11.factory.exception.DeviceNotInFactory;
import com.grupo11.smartdevice.SmartDevice;
import com.grupo11.smartdevice.SmartDeviceModel;
import com.grupo11.smartdevice.exception.DeviceAlreadyExist;
import com.grupo11.smartdevice.exception.DeviceNotFound;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FactoryModel implements Serializable {
    /**
     * Armazem de todos os dispositivos criados, quer estejam numa casa ou não
     */
    private SmartDeviceModel smartDeviceRepository;
    /**
     * Mapa que indiqua se um dispositivo (pelo seu código de fábrica ) está disponivel(true se sim false se não) para ser adicionado por uma casa
     */
    private Map<String, Boolean> available;
    /**
     * Endereço da fábrica
     */
    private String address; // Para este trabalho não nos interessa, mas poderiamos utilizar isto para criar várias fábricas

    /**
     * Construtor de fabricas
     */
    public FactoryModel() {
        this.smartDeviceRepository = new SmartDeviceModel();
        this.available = new HashMap<>();
        this.address = "FACTORY ADDRESS";
    }

    /**
     * Metodo que partilha o apontador de o smartDeviceRepository que a fabrica tem
     *
     *
     * @return o apontador de o smartDeviceRepository da fabrica
     */
    public SmartDeviceModel getSmartDeviceRepository() {
        return this.smartDeviceRepository;
    }

    public void setSmartDeviceRepository(SmartDeviceModel smartDeviceRepository) {
        this.smartDeviceRepository = smartDeviceRepository;
    }

    /**
     * Metodo que atualiza o código de fábrica de um dispositivo no mapa de available
     *
     * @param OldFactoryCode o código de fábrica antigo do dispositivo
     * @param NewFactoryCode o novo código de fábrica do dispositivo
     * @throws DeviceNotFound se o smartDevice não existir
     * @throws DeviceNotInFactory se o smartDevice não existir na fabrica
     */
    public void updateDevice(String OldFactoryCode,String NewFactoryCode) throws DeviceNotFound,DeviceNotInFactory {
        if (this.available.get(OldFactoryCode) == null)
            throw new DeviceNotFound("O dispositivo de código de fábrica " + OldFactoryCode + " não foi encontrado!!");

        if (this.available.get(OldFactoryCode) == false)
            throw  new DeviceNotInFactory( "O dispositivo de código de fábrica " + OldFactoryCode +" está numa casa" );


        if (!NewFactoryCode.equals("#")) {
            this.available.remove(OldFactoryCode);
            this.available.put(NewFactoryCode, true);
        }
    }

    /**
     * Metodo que apaga um device do mapa de available
     *
     * @param factoryCode o código de fábrica do dispovitivo a apagar
     * @throws DeviceNotFound se o dispositivo não existir
     * @throws DeviceNotInFactory se o dispositivo não existir na fabrica
     */
    public void deleteDevice(String factoryCode)throws DeviceNotFound, DeviceNotInFactory {
        if (this.available.get(factoryCode) == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+factoryCode+ " não foi encontrado!!");
        if (this.available.get(factoryCode) == false) throw new DeviceNotInFactory("O dispositivo de código de fábrica "+factoryCode+ "encontra-se numa casa");

        this.available.remove(factoryCode);
    }

    /**
     * Metodo que adiciona um dispositivo ao mapa de available
     *
     * @param factoryCode o código de fábrica do dispositivo
     * @param available é true se o dispositivo estiver disponivel e false quando não estiver disponivel
     * @throws DeviceAlreadyExist se o dispositivo já existe
     */
    public void setDeviceAvailability(String factoryCode, Boolean available) throws DeviceAlreadyExist {
        this.available.put(factoryCode, available);
    }

    /**
     * Metodo que diz se um divice está disponivel ou não
     *
     * @param factoryCode o código de fábrica do dispositivo
     * @return é true se o dispositivo estiver disponivel e false quando não estiver disponivel
     * @throws DeviceNotFound se o dispositivo não existe
     */
    public boolean isDeviceAvailable(String factoryCode)throws DeviceNotFound {
        return available.get(factoryCode);
    }

    /**
     * Metodo que dá uma lista de todos os SmartDevice disponiveis (available == true)
     *
     * @return uma lista de todos os SmartDevice disponiveis
     */
    public List<SmartDevice> onlyDeviceAvailable (){
       return this.smartDeviceRepository.findAllSmartDevices().stream().filter(x-> this.available.get(x.getFactoryCode())).collect(Collectors.toList());
    }
}
