package com.grupo11.energysupplier;

import com.grupo11.energysupplier.exception.EnergySupplierNotFound;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class EnergySupplierView {
    
    /*
    ** Função que verifica se a opção selecionada de uma determinado menu é aceite
    */
    
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

    /*
    ** Menu Principal dos Energy Suppliers
    */
    
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
    
    /* 
    ** Menu para Adicionar um Energy Supplier
    */
                         
    public String[] addEnergySupplierMenu() {
        System.out.println(("-----------Adicionar um Fornecedor de Energia-----------\n\n"));
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do fornecedor (para cancelar digite *): ");
        input[0] = scanner.nextLine();
        if (input[0].equals("*")) return null;
        input[1] = updateEnergySupplierFormulaMenu();
        if (input[1].equals("*")) return null;
        return input;
    }
    
    /*
    ** Menu para Remover um EnergySupplier
    */
                         
    public String removeEnergySupplierMenu(EnergySupplierModel energySupplierRepository) {
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
                         
    /*
    ** Menu para Selecionar um determinado EnergySupplier 
    */
    
    public String selectEnergySupplierMenu(EnergySupplierModel energySupplierRepository) {
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
        try {
            energySupplierRepository.findEnergySupplierByName(input);
        } catch (EnergySupplierNotFound e) {
            System.out.println("Opção inválida, selecione uma opção da lista!");
            return null;
        }
        if (input.equals("*")) return null;
        return input;
    }
    
    /*
    ** Menu de atualização de um EnergySupplier (Podemos mudar Nome e Formula)
    */
                         
    public int updateEnergySupplierMenu() {
        System.out.print( """
                -----------Atualizar Fornecedor de Energia-----------
                1. Nome
                2. Fórmula
                3. Voltar
                
                Sua opção (Selecione um número):\s""");
        return optionsValidation(3);
    }
                         
    /* 
    ** Menu de atualização do Nome de um EnergySupplier 
    */
                         
    public String updateEnergySupplierNameMenu() {
        System.out.print("Insira o novo nome do fornecedor de energia (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }

    /* 
    ** Menu de atualização da Formula de um EnergySupplier 
    */
                         
    public String updateEnergySupplierFormulaMenu() {
        String sb = """
                
                -----------Regras na criação das fórmulas-----------

                * Ambas os valores das variáveis do ponto 1 e 2 estão definidas no arranque do sistema, deves apenas utiliza-las

                1 - Deverá ser utilzada a variável ValorBase que é o custo diário do kwh de energia
                2 - Deverá ser utilizada a variável Imposto que é o factor multiplicativo dos impostos
                3 - Deverá ser utilizada a variável ConsumoDispositivo que é o gasto energético do dispositivo
                4 - Opcionalmente podes utilizar a variável numeroDispositivos e eventualmente utilizar algum if-then-else para manipular a formula

                Digite a fórmula seguindo as regras (para cancelar digite *):\s""";
        Scanner scanner = new Scanner(System.in);
        System.out.print(sb);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }
                         
    /* 
    **Apresenta todos os EnergySuppliers Existentes
    */
                         
    public void listSmartHouses(List<EnergySupplier> energySuppliers) {
        if (energySuppliers.size() == 0) System.out.println("Não há nenhum fornecedor de energia");
        StringBuilder sb = new StringBuilder();
        energySuppliers.forEach(energySupplier -> sb.append(energySupplier).append("\n\n"));
        System.out.print(sb);
    }
}
