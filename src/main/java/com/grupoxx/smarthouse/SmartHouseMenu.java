package com.grupoxx.smarthouse;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.EnergySupplier.EnergySupplierRepository;
import com.grupoxx.smartdevice.SmartDevice;

import java.util.List;
import java.util.Scanner;

public class SmartHouseMenu {
    public static int smartHouseMenu() {
        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Listar \n");
        sb.append("5. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.close();
        return option;
    }

    public static String[] smartHouseCreateMenu() {
        String[] input = new String[2];
        System.out.print("-----------Criar Casa-----------\n\nEscreva o Endereço (para cancelar a criação digite *): ");
        Scanner scanner = new Scanner(System.in);
        input[0] = scanner.nextLine();
        scanner.next();
        System.out.print("Deseja adicionar mais informações a casa agora? (S ou N)");
        input[1] = scanner.nextLine();
        scanner.close();
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
        String input = scanner.nextLine();
        scanner.close();
        return input;
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
        scanner.close();
        return input;
    }

    // Esta função faz mais sentido dentro do menu do energySupplier
    public static String smartHouseSelectEnergySupplierMenu(EnergySupplierRepository energySupplierRepository) {
        List<EnergySupplier> energySuppliers = energySupplierRepository.findAllEnergySuppliers();
        StringBuilder sb = new StringBuilder("-----------Selecionar Fornecedor de Energia-----------\n\n");
        energySuppliers.forEach(energySupplier -> sb.append(energySupplier).append("\n"));
        sb.append("Para cancelar a ação digite *\n");
        sb.append("Selecione o fornecedor de energia (pelo nome): ");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    // Esta função faz mais sentido dentro do menu do smartDevice

    public static String smartHouseSelectSmartDeviceMenu(SmartHouseRepository smartHouseRepository) {
        List<SmartDevice> smartDevices = smartDevices.findAll
        StringBuilder sb = new StringBuilder("-----------Selecionar Dispositivo-----------\n\n");
        smartDevices.forEach(smartDevice -> sb.append(smartDevice).append("\n"));
        sb.append("Para cancelar a ação digite *\n");
        sb.append("Selecione o dispositivo (pelo código do dispositivo): ");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
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
        scanner.close();
        return option;
    }
    public static String smartHouseAddRoamMenu() {
        StringBuilder sb = new StringBuilder("-----------Adicionar Divisão na Casa-----------\n\n");
        sb.append("Nome da divisão (Para cancelar digite *): ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}
