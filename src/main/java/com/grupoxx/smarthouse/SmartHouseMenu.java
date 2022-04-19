package com.grupoxx.smarthouse;

import com.grupoxx.energysupplier.EnergySupplierMenu;
import com.grupoxx.energysupplier.EnergySupplierRepository;
import com.grupoxx.factory.Factory;
import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;

import java.util.*;

public class SmartHouseMenu {

    private int optionsValidation(int options) {
        Scanner scanner = new Scanner(System.in);
        String s = "Opção inválida, digite um valor inteiro entre 1 e " + String.valueOf(options);
        try {
            int option = scanner.nextInt();
            if (option < 1 || option > options) {
                System.out.println(s);
                return -1;
            }
            return option;
        } catch (InputMismatchException e) {
            System.out.println(s);
            return -1;
        }
    }

    /**
     * Menu principal da classe SmartHouseMenu.
     * Oferece opções genéricas que serão mais tarde tratadas por menus específicos
     *
     * @return a opção selecionado pelo usuário, ou -1, caso a opção seja inválida
     */

    public int smartHouse() {
        String sb = """
                -----------Casa-----------

                1. Adicionar Casa
                2. Remover Casa
                3. Atualizar dados da casa
                4. Listar Casas\s
                5. Voltar\s

                Sua Opção (Selecionar Número):\s""";
        System.out.print(sb);
        return optionsValidation(5);
    }

    /**
     * Menu para adicionar uma casa
     *
     * @return o endereço da casa inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação
     */
    public String addSmartHouse() {
        System.out.print("-----------Criar Casa-----------\n\n");
        return updateAddress();
    }

    /**
     * Menu que oferece ao usuário a possibilidade de adicionar mais informações na criação da casa
     *
     * @return a resposta do usuário a pergunta
     */

    public String addMoreInformationSmartHouse() {
        System.out.print("Deseja adicionar mais informações a casa agora? (S - Sim, N - Não): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (!(input.equals("N") || input.equals("S"))) System.out.println("Opção inválida\n");
        return input;
    }

    /**
     * Menu para remover uma casa
     *
     * @param smartHouseRepository o repositório de casas onde queremos remover uma casa
     * @return o endereço da casa inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação
     */
    public String removeSmartHouse(SmartHouseRepository smartHouseRepository) {
        System.out.print("-----------Remover Casa-----------\n\n");
        return selectSmartHouse(smartHouseRepository);
    }

    /**
     * Menu para selecionar uma casa
     *
     * @param smartHouseRepository o repositório de casas que neste contexto representa as casas que podemos selecionar
     * @return o endereço da casa inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação
     */

    public String selectSmartHouse(SmartHouseRepository smartHouseRepository) {
        List<SmartHouse> smartHouses = smartHouseRepository.findAllSmartHouses();
        if (smartHouses.size() == 0) {
            System.out.println("Não há nenhuma casa");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Selecionar Casa-----------\n\n");
        smartHouses.forEach(smartHouse -> sb.append(smartHouse).append("\n\n"));
        sb.append("Selecione a casa pelo endereço (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Mostra na tela todas as informações das casas que estão na lista
     *
     * @param smartHouses a lista que contém as casas que vamos mostrar na tela
     */

    public void listSmartHouses(List<SmartHouse> smartHouses) {
        if (smartHouses.size() == 0) System.out.println("Não há nenhuma casa");
        StringBuilder sb = new StringBuilder();
        smartHouses.forEach(smartHouse -> sb.append(smartHouse).append("\n\n"));
        System.out.print(sb);
    }

    /**
     * Menu para adicionar uma divisão a uma casa
     *
     * @return O nome da divisão da casa inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação
     *         de escrita
     */

    public String addRoom() {
        System.out.print("Nome da divisão (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Menu para remover uma divisão de uma casa
     *
     * @param smartHouseRepository o repositório de casas
     * @param address o endereço da casa que queremos remover uma divisão
     * @return o nome da divisão da casa inserido pelo usuário, ou null, caso o deseje cancelar a operação
     */

    public String removeRoom(SmartHouseRepository smartHouseRepository, String address) {
        List<String> rooms = smartHouseRepository.findAllRoomsFromSmartHouse(address);
        if (rooms.size() == 0) {
            System.out.println("Não há nenhuma divisão nesta casa");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Remover Divisão-----------\n\n");
        rooms.forEach(room -> sb.append(room).append("\n"));
        sb.append("\nSelecione a divisão pelo nome (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Menu para adicionar um dispositivo a uma casa
     *
     * @param factory A fábrica que produz os dispositivos. Ela sabe quais dispositivos estão disponíveis para serem
     *                adicionados
     * @return o código de fábrica inserido pelo usuário, ou null, casa o usuário deseje cancelar a operação
     *         ou caso não tenha nenhum dispositivo disponível para adicionar
     */

    public String addDevice(Factory factory) {
        List<SmartDevice> availableDevices = factory.getSmartDeviceRepository().findAllSmartDevices().stream().
                filter(device -> factory.isDeviceAvailable(device.getFactoryCode())).toList();
        if (availableDevices.size() == 0) {
            System.out.println("Não há nenhum dispositivo disponível para adicionar na casa");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Dispositivos-----------\n\n");
        availableDevices.forEach(device -> sb.append(device).append("\n\n"));
        sb.append("Selecione o dispositivo pelo código de fábrica (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("*")) return null;
        if (!factory.isDeviceAvailable(input)) {
            System.out.println("Dispositivo inserido não está disponível para adicionar a uma casa");
            return null;
        }
        return input;
    }

    /**
     * Menu para remover um dispositivo da casa
     *
     * @param smartDeviceRepository um repositório de dispositivos, que no contexto de remoção de um dispositivo
     *                              de uma casa representa uma divisão da casa
     * @return o código de fábrica inserido pelo usuário, ou null, casa o usuário deseje cancelar a operação
     *         ou caso não tenha nenhum dispositivo disponível para remover
     */
    public String removeSmartDeviceMenu(SmartDeviceRepository smartDeviceRepository) {
        List<SmartDevice> smartDevices = smartDeviceRepository.findAllSmartDevices();
        if (smartDevices.size() == 0) {
            System.out.println("Não há nenhum dispositivo nesta divisão");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Dispositivos-----------\n\n");
        smartDevices.forEach(device -> sb.append(device).append("\n\n"));
        sb.append("Digite o código de fábrica do dispositivo (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Menu com opções para atualizar dados da casa
     *
     * @return a opção selecionado pelo usuário, ou -1, caso a opção seja inválida
     */

    public int updateSmartHouse() {
        String sb = """
                -----------Atualizar Casa-----------

                1. Adicionar Divisão
                2. Remover Divisão
                3. Dispositivos (criar, remover, ligar/desligar)
                4. Atualizar Endereço
                5. Atualizar Fornecedor de Energia
                6. Atualizar Proprietário
                7. Voltar

                Sua Opção (Selecionar Número):\s""";
        System.out.print(sb);
        return optionsValidation(7);
    }

    public String selectRoom(SmartHouseRepository smartHouseRepository, String address) {
        List<String> rooms = smartHouseRepository.findAllRoomsFromSmartHouse(address);
        if (rooms.size() == 0) {
            System.out.println("A casa precisa ter divisões para gerenciar dispositivos");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Selecionar Divisão-----------\n\n");
        rooms.forEach(room -> sb.append(room).append("\n"));
        sb.append("Selecione a divisão da casa pelo nome (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    public int updateSmartDevices() {
        String sb = """
                -----------Dispostivos-----------
                
                1. Adicionar dispositivo
                2. Remover dispositivo
                3. Ligar todos os dispositivos desta divisão
                4. Desligar todos os dispositivos desta divisão
                5. Ligar um dispositivo desta divisão
                6. Desligar um dispositivo desta divisão
                7. Voltar
                
                Sua opção (Selecionar Número):\s""";
        System.out.print(sb);
        return optionsValidation(7);
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir um endereço de uma casa para o usuário
     *
     * @return O endereço inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação de escrita
     */
    public String updateAddress() {
        System.out.print("Endereço (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir os dados de um proprietário, isto é, nome e nif
     *
     * @return Um array de strings, onde na primeira posição está o nome, e na segunda o nif inserido pelo usuário,
     *         ou null, caso o usuário deseje cancelar a operação de escrita
     */

    public String[] updateOwner() {
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do proprietário (para cancelar digite *): ");
        input[0] = scanner.nextLine();
        if (input[0].equals("*")) return null;
        scanner.nextLine();
        System.out.print("NIF do proprietário (para cancelar digite *): ");
        input[1] = scanner.next();
        if (input[1].equals("*")) return null;
        return input;
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir o nome do fornecedor de energia da casa para o usuário
     *
     * @return O nome do fornecedor de energia inserido pelo usuário, ou null, caso o usuário deseje cancelar
     *         a operação
     */

    public String updateEnergySupplierMenu(EnergySupplierRepository energySupplierRepository, String oldEnergySupplier) {
        EnergySupplierMenu menu = new EnergySupplierMenu();
        String selectedEnergySupplier = menu.selectEnergySupplierMenu(energySupplierRepository);
        if (selectedEnergySupplier.equals(oldEnergySupplier)) {
            System.out.println("Este já o teu fornecedor de energia");
            return null;
        }
        if (selectedEnergySupplier.equals("*")) return null;
        return selectedEnergySupplier;
    }
}
