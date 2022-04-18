package com.grupoxx.main;

import java.util.Scanner;

public class MainMenu {
    public static int mainMenu() {
        String sb = """
                -----------Menu Principal-----------

                1. Fornecedores de Energia
                2. Casas
                3. Dispositivos
                4. Iniciar Simulação Manual
                5. Iniciar Simulação Automática
                6. Carregar Estado
                7. Guardar Estado
                8. Sair

                Sua Opção (Selecionar Número):\s""";
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
