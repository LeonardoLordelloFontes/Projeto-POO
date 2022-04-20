package com.grupoxx.energysupplier;

import com.grupoxx.smarthouse.SmartHouse;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class EnergySupplierMenu {

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

    public int energySupplierMenu() {
        System.out.print("""
                -----------Fornecedor de Energia-----------

                1. Adicionar\s
                2. Remover\s
                3. Atualizar\s
                4. Listar
                5. Voltar\s

                Sua Opção (Selecionar Número):\s""");
        return optionsValidation(5);
    }

    public String[] addEnergySupplierMenu() {
        System.out.println(("-----------Adicionar um Fornecedor de Energia-----------\n\n"));
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do fornecedor (para cancelar digite *): ");
        input[0] = scanner.nextLine();
        if (input[0].equals("*")) return null;
        System.out.print("Formula (para cancelar digite *): ");
        input[1] = scanner.nextLine();
        if (input[1].equals("*")) return null;
        return input;
    }

    public String removeEnergySupplierMenu(EnergySupplierRepository energySupplierRepository) {
        List<EnergySupplier> energySuppliers = energySupplierRepository.findAllEnergySuppliers();
        if (energySuppliers.size() == 0) {
            System.out.println("Lista de Fornecedores de Energia vazia");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Remover Fornecedor de Energia-----------\n\n");
        energySuppliers.forEach(device -> sb.append(device).append("\n\n"));
        sb.append("Digite o nome do Fornecedor (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("*")) return null;
        return input;
    }

    public String selectEnergySupplierMenu(EnergySupplierRepository energySupplierRepository) {
        List<EnergySupplier> energySuppliers = energySupplierRepository.findAllEnergySuppliers();
        if (energySuppliers.size() == 0) {
            System.out.println("Não há fornecedores de energia disponíveis");
            return null;
        }
        StringBuilder sb = new StringBuilder("-----------Selecionar Fornecedor de Energia-----------\n\n");
        energySuppliers.forEach(energySupplier -> sb.append(energySupplier).append("\n"));
        sb.append("Selecione o fornecedor de energia pelo nome (para cancelar digite *): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (energySupplierRepository.getEnergySuppliers().get(input) == null) {
            System.out.println("Opção inválida, selecione uma opção da lista!");
            return null;
        }
        if (input.equals("*")) return null;
        return input;
    }

    public int updateEnergySupplierMenu() {
        System.out.print( """
                -----------Atualiazar Fornecedor de Energia-----------
                1. Nome
                2. Fórmula
                3. Voltar
                
                Sua opção (Selecione um número):\s""");
        return optionsValidation(3);
    }

    public String updateEnergySupplierNameMenu() {
        System.out.print("Insira o novo nome do fornecedor de energia (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    public String updateEnergySupplierFormulaMenu() {
        System.out.print("Insira a nova fórmula do fornecedor de energia (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    public void listSmartHouses(List<EnergySupplier> energySuppliers) {
        if (energySuppliers.size() == 0) System.out.println("Não há nenhum fornecedor de energia");
        StringBuilder sb = new StringBuilder();
        energySuppliers.forEach(energySupplier -> sb.append(energySupplier).append("\n\n"));
        System.out.print(sb);
    }
}
