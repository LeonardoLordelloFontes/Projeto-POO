package com.grupoxx.smarthouse;
import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.energysupplier.exception.EnergySupplierNotFound;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.exception.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que permite a manipulação de um grupo de casas.
 * Permite adicionar, remover e obter casas, bem como atualizar dados de uma casa
 *
 * @author Leonardo Lordello Fontes
 * @author Zé (completar)
 * @author Adriano (completar)
 */

public class SmartHouseRepository implements Serializable {

    /**
     * Estrutura de dados responsável pelo armazenamento das casas
     * Cada chave é uma String que representa o endereço de uma casa. Cada endereço é único.
     * A cada chave faz corresponder uma casa inteligente
     */

    private final Map<String, SmartHouse> smartHouses;

    /**
     * Construtor que cria a estrutura de dados responsável pelo armazenamento das casas
     */

    public SmartHouseRepository() {
        this.smartHouses = new HashMap<>();
    }

    /**
     * Permite obter a casa pelo endereço dela
     *
     * @param address o endereço da casa
     * @return Se existir, a casa que tem o endereço passado por argumento
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     */

    public SmartHouse findHouseByAddress(String address) throws HouseNotFound {
        SmartHouse smartHouse = smartHouses.get(address);
        if (smartHouse == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        return smartHouse;
    }

    /**
     * Permite obter um repositório de dispositivos inteligentes de uma divisão específica de uma casa
     *
     * @param address o endereço da casa
     * @param room o nome da divisão da casa
     * @return um repositório de dispositivos inteligentes
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     * @throws RoomNotFound caso o nome da divisão passado por argumento não existir na casa
     */

    public SmartDeviceRepository findSmartDevicesByRoom(String address, String room) throws HouseNotFound, RoomNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        SmartDeviceRepository smartDeviceRepository = smartHouses.get(address).getSmartDevices().get(room);
        if (smartDeviceRepository == null)
            throw new RoomNotFound("A divisão com o nome " + room + " não foi encontrada");
        return smartDeviceRepository;
    }

    /**
     * Adiciona uma casa ao repositório de casas
     *
     * @param address o endereço da casa que deseja adicionar
     * @throws HouseAddressAlreadyExists caso o endereço da casa passado por argumento já exista no repositório
     */

    public void addSmartHouse(String address) throws HouseAddressAlreadyExists {
        if (smartHouses.get(address) != null)
            throw new HouseAddressAlreadyExists("A casa com o endereço " + address + " já existe");
        SmartHouse smartHouse = new SmartHouse(address);
        smartHouses.put(address, smartHouse);
    }

    /**
     * Adiciona uma divisão a uma casa
     *
     * @param address o endereço da casa
     * @param room o nome da divisão da casa que deseja adicionar
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     * @throws RoomAlreadyExists caso o nome da divisão passado por argumento já exista na casa
     */

    public void addRoomToHouse(String address, String room) throws HouseNotFound, RoomAlreadyExists {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        if (smartHouses.get(address).getSmartDevices().get(room) != null)
            throw new RoomAlreadyExists("A divisão " + room + " já existe nesta casa");
        smartHouses.get(address).getSmartDevices().put(room, new SmartDeviceRepository());
    }

    /**
     * Remove uma divisão de uma casa
     *
     * @param address o endereço da casa
     * @param room o nome da divisão da casa que deseja remover
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     * @throws RoomNotFound caso o nome da divisão passado por argumento não existir na casa
     */


    public void removeRoomFromHouse(String address, String room) throws HouseNotFound, RoomNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        if (smartHouses.get(address).getSmartDevices().get(room) == null)
            throw new RoomNotFound("A divisão com o nome " + room + " não foi encontrada");
        smartHouses.get(address).getSmartDevices().remove(room);
    }

    /**
     * Remove uma casa pelo endereço dela
     *
     * @param address o endereço da casa que deseja remover
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     */

    public void removeHouseByAddress(String address) throws HouseNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        smartHouses.remove(address);
    }

    /**
     * Atualiza o endereço de uma casa
     *
     * @param oldAddress o endereço atual da casa
     * @param newAddress o novo endereço da casa
     * @throws HouseNotFound caso o endereço da casa atual passado como argumento não existir
     * @throws HouseAddressAlreadyExists caso já exista uma casa com o endereço que queremos atualizar
     */

    public void updateHouseAddress(String oldAddress, String newAddress) throws HouseNotFound, HouseAddressAlreadyExists {
        if (smartHouses.get(oldAddress) == null)
            throw new HouseNotFound("A casa com o endereço " + oldAddress + " não foi encontrada");
        if (smartHouses.get(newAddress) != null)
            throw new HouseAddressAlreadyExists("A casa com o endereço " + newAddress + " já existe");
        smartHouses.get(oldAddress).setAddress(newAddress);
    }

    /**
     * Atualizar o fornecedor de energia de uma casa
     *
     * @param energySupplierRepository um repositório de fornecedores de energia
     * @param address o endereço da casa
     * @param newEnergySupplier o nome do novo fornecedor de energia
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     * @throws EnergySupplierNotFound caso o nome do fornecedor de energia não seja encontrado no repositório de
     *                                fornecedores de energia
     */

    public void updateEnergySupplier(EnergySupplierRepository energySupplierRepository,
                                     String address,
                                     String newEnergySupplier) throws HouseNotFound, EnergySupplierNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        if (energySupplierRepository.getEnergySuppliers().get(newEnergySupplier) == null)
            throw new EnergySupplierNotFound("O fonecedor de energia " + newEnergySupplier + " não foi encontrado");
        smartHouses.get(address).setEnergySupplier(newEnergySupplier);
    }

    /**
     * Atualiza o proprietário da casa
     *
     * @param address o endereço da casa
     * @param newOwner o novo proprietário da casa
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     */

    public void updateOwner(String address, Owner newOwner) throws HouseNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        smartHouses.get(address).setOwner(newOwner);
    }

    /**
     * Permite obter as casas do repositório de casas
     *
     * @return a lista das casas do repositório
     */

    public List<SmartHouse> findAllSmartHouses() {
        return new ArrayList<>(smartHouses.values());
    }

    /**
     * Permite obter as casas que tem um determinado fornecedor de energia
     *
     * @param energySupplier o nome do fornecedor de energia
     * @return a lista das casas com um determinado forncedor de energia
     */

    public List<SmartHouse> findSmartHousesByEnergySupplier(String energySupplier) {
        return smartHouses.values().stream()
                .filter(smartHouse -> smartHouse.getEnergySupplier().equals(energySupplier)).toList();
    }

    /**
     * Permite obter as divisões de uma casa
     *
     * @param address o endereço da casa
     * @return a lista das divisões da casa
     * @throws HouseNotFound caso o endereço da casa passado por argumento não existir
     */

    public List<String> findAllRoomsFromSmartHouse(String address) throws HouseNotFound {
        if (smartHouses.get(address) == null)
            throw new HouseNotFound("A casa com o endereço " + address + " não foi encontrada");
        return new ArrayList<>(smartHouses.get(address).getSmartDevices().keySet());
    }

    /**
     * Permite obter o número de dispositivos de uma casa
     *
     * @param address o endereço da casa que desejamos obter o número de dispositivos
     * @return o número de dispositivos de uma casa
     */

    public int findNumberOfDevicesSmartHouse(String address) {
        return smartHouses.get(address).getSmartDevices().values().
                stream().mapToInt(smartDeviceRepository -> smartDeviceRepository.findAllSmartDevices().size()).sum();
    }
}
