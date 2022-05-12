package com.grupo11.main;

import java.util.Scanner;

public class MainView {
    
    /*
    * Menu Principal do Projeto
    */
    
    public static int mainMenu() {
        String sb = """
                -----------Menu Principal-----------

                1. Fornecedores de Energia
                2. Casas
                3. Dispositivos
                4. Iniciar Simulação Manual
                5. Iniciar Simulação Automática
                6. Estado do programa
                7. Sair

                Sua Opção (Selecionar Número):\s""";
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
