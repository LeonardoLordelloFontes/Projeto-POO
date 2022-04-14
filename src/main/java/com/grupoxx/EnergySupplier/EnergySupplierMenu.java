package com.grupoxx.EnergySupplier;

import java.util.List;
import java.util.Scanner;

public class EnergySupplierMenu {

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
}
