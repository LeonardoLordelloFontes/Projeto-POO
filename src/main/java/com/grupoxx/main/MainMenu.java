package com.grupoxx.main;

import java.util.Scanner;

public class MainMenu {
    public static int mainMenu() {
        StringBuilder sb = new StringBuilder("-----------Menu Principal-----------\n\n");
        sb.append("1. Fornecedores de Energia \n");
        sb.append("2. Casas \n");
        sb.append("3. Dispositivos \n");
        sb.append("4. Iniciar Simulação Manual \n");
        sb.append("5. Iniciar Simulação Automática \n");
        sb.append("6. Carregar Estado \n");
        sb.append("7. Guardar Estado \n");
        sb.append("8. Sair \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
