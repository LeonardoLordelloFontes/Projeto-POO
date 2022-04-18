package com.grupoxx.EnergySupplier;

import java.util.List;
import java.util.Scanner;

public class EnergySupplierMenu {

    public static String smartHouseSelectEnergySupplierMenu(EnergySupplierRepository energySupplierRepository) {
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
