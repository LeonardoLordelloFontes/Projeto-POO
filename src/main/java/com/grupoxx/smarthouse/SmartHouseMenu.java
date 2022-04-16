package com.grupoxx.smarthouse;

import jdk.jshell.Snippet;

import java.util.List;
import java.util.Scanner;

public class SmartHouseMenu {

    /**
     *
     * @return
     */

    public static int smartHouseMenu() {
        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar Casa\n");
        sb.append("2. Remover Casa\n");
        sb.append("3. Atualizar dados da casa\n");
        sb.append("4. Listar Casas \n");
        sb.append("5. Ligar/Desligar dispositivos \n\n");
        sb.append("6. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option < 1 || option > 6) return -1;
        return option;
    }

    public static String[] smartHouseAddMenu() {
        String[] input = new String[2];
        System.out.print("-----------Criar Casa-----------\n\nEscreva o Endereço (para cancelar a criação digite *): ");
        Scanner scanner = new Scanner(System.in);
        input[0] = scanner.nextLine();
        if (input[0].equals("*")) return null;
        System.out.print("Deseja adicionar mais informações a casa agora? (S ou N): ");
        input[1] = scanner.nextLine();
        return input;
    }

    public static String smartHouseSelectHousesMenu(SmartHouseRepository smartHouseRepository) {
        List<SmartHouse> smartHouses = smartHouseRepository.findAllSmartHouses();
        if (smartHouses.size() == 0) return null;
        StringBuilder sb = new StringBuilder("-----------Selecionar Casa-----------\n\n");
        smartHouses.forEach(smartHouse -> sb.append(smartHouse).append("\n"));
        sb.append("Para cancelar a ação digite *\n");
        sb.append("Selecione a casa (pelo endereço): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String smartHouseSelectRoomsMenu(SmartHouse smartHouse) {
        List<String> rooms = smartHouse.getRooms();
        StringBuilder sb = new StringBuilder("-----------Selecionar Divisão-----------\n\n");
        rooms.forEach(room -> sb.append(room).append("\n"));
        sb.append("Para cancelar a ação digite *\n");
        sb.append("Selecione a divisão (pelo nome): ");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Menu com opções para atualizar dados da casa
     */

    public static int smartHouseUpdateMenu() {
        StringBuilder sb = new StringBuilder("-----------Atualizar Casa-----------\n\n");
        sb.append("1. Adicionar Divisão \n");
        sb.append("2. Adicionar Dispositivo \n");
        sb.append("3. Remover Divisão \n");
        sb.append("4. Remover Dispositivo \n");
        sb.append("5. Atualizar Endereço \n");
        sb.append("6. Atualizar Fornecedor de Energia \n");
        sb.append("7. Atualizar Proprietário \n");
        sb.append("8. Voltar \n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option < 1 || option > 8) return -1;
        return option;
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir um endereço de uma casa para o usuário
     * @return O endereço inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação de escrita
     */
    public static String smartHouseUpdateAddressMenu() {
        System.out.print("Endereço (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir os dados de um proprietário, isto é, nome e nif
     * @return Um array de strings, onde na primeira posição está o nome, e na segunda o nif inserido pelo usuário,
     *         ou null, caso o usuário deseje cancelar a operação de escrita
     */

    public static String[] smartHouseUpdateOwnerMenu() {
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome (para cancelar digite *): ");
        input[0] = scanner.nextLine();
        if (input[0].equals("*")) return null;
        scanner.nextLine();
        System.out.print("NIF (para cancelar digite *): ");
        input[1] = scanner.next();
        if (input[1].equals("*")) return null;
        return input;
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir o nome da divisão de uma casa para o usuário
     * @return O nome da divisão da casa inserido pelo usuário, ou null, caso o usuário deseje cancelar a operação
     *         de escrita
     */

    public static String smartHouseUpdateRoomMenu() {
        System.out.print("Nome da divisão (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /**
     * Menu que pode ser utilizado sempre que precisarmos pedir o nome do fornecedor de energia da casa para o usuário
     * @return O nome do fornecedor de energia inserido pelo usuário, ou null, caso o usuário deseje cancelar
     *         a operação
     */

    public static String smartHouseUpdateEnergySupplierMenu() {
        System.out.println("Nome do fornecedor de energia (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }
}
