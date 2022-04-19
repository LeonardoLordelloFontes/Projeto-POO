package com.grupoxx.EnergySupplier;

import java.util.List;
import java.util.Scanner;

public class EnergySupplierMenu {

    public String updateEnergySupplierFormulaMenu() {
        String sb = """
                -----------Regras na criação das fórmulas-----------
                
                * Ambas os valores das variáveis do ponto 1 e 2 estão definidas no arranque do sistema, deves apenas utiliza-las
                
                1 - Deverá ser utilziada a variável ValorBase que é o custo diário do kwh de energia
                2 - Deverá ser utilizada a variável Imposto que é o factor multiplicativo dos impostos
                3 - Deverá ser utilizada a variável ConsumoDispositivo que é o gasto energético do dispositivo
                4 - Opcionalmente podes utilizar a variável numeroDispositivos e eventualmente utilizar algum if-then-else para manipular a formula

                Digite a fórmula seguindo as regras (para cancelar digite *):\s""";
        Scanner scanner = new Scanner(System.in);
        System.out.println(sb);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }
    public int updateEnergySupplier() {
        String sb = """
                -----------Atualizar Fornecedor de Energia-----------
                
                """
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
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("*")) return null;
        return input;
    }
}
