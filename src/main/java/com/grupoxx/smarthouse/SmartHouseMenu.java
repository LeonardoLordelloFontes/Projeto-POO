package com.grupoxx.smarthouse;

import java.util.List;
import java.util.Scanner;

import static com.grupoxx.main.MainMenu.clearScreen;

public class SmartHouseMenu {
    public static int smartHouseMenu() {
        // clearScreen();
        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Listar \n");
        sb.append("5. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String[] smartHouseAddMenu() {
        String[] input = new String[2];
        System.out.print("-----------Criar Casa-----------\n\nEscreva o Endereço (para cancelar a criação digite *): ");
        Scanner scanner = new Scanner(System.in);
        input[0] = scanner.nextLine();
        scanner.next();
        System.out.print("Deseja adicionar mais informações a casa agora? (S ou N)");
        input[1] = scanner.nextLine();
        return input;
    }

    public static String smartHouseSelectHousesMenu(SmartHouseRepository smartHouseRepository) {
        List<SmartHouse> smartHouses = smartHouseRepository.findAllSmartHouses();
        StringBuilder sb = new StringBuilder("-----------Selecionar Casa-----------\n\n");
        smartHouses.forEach(smartHouse -> sb.append(smartHouse).append("\n"));
        sb.append("Para cancelar a ação digite *\n");
        sb.append("Selecione a casa (pelo endereço): ");
        System.out.println(sb);
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

    public static int smartHouseUpdateMenu() {
        StringBuilder sb = new StringBuilder("-----------Atualizar Casa-----------\n\n");
        sb.append("1. Adicionar Divisão \n");
        sb.append("2. Adicionar Dispositivo \n");
        sb.append("3. Remover Divisão \n");
        sb.append("4. Remover Dispositivo \n");
        sb.append("5. Atualizar Endereço \n");
        sb.append("6. Atualizar Fornecedor de Energia \n");
        sb.append("7. Atualizar Proprietário \n");
        sb.append("8. Atualizar dispositivo \n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        return option;
    }
    public static String smartHouseAddRoamMenu() {
        StringBuilder sb = new StringBuilder("-----------Adicionar Divisão na Casa-----------\n\n");
        sb.append("Nome da divisão (Para cancelar digite *): ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static String[] smartHouseUpdateOwnerMenu() {
        String input[] = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("-----------Adicionar Divisão na Casa-----------\n\nNIF (para cancelar digite *): ");
        input[0] = scanner.next();
        if (input[0].equals("*")) return null;
        scanner.nextLine();
        System.out.print("Nome (para cancelar digite *): ");
        input[1] = scanner.nextLine();
        if (input[1].equals("*")) return null;
        return input;
    }
}
