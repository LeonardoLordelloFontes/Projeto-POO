package com.grupoxx.menu;
import java.util.*;

import static java.lang.Integer.parseInt;


public abstract class MainMenuToRemove {

    public static void clearWindow() {

        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    public static int MenuPósSimulação() {
        StringBuilder sb = new StringBuilder("-----------Pós Simulação-----------\n\n");
        sb.append("1. Listar faturas emitidas \n");
        sb.append("2. Estatistica da simulação \n");
        sb.append("3. Voltar ao Menu Inicial \n");
        sb.append("4. Sair \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int MenuMudançaDeEstado() {
        // sugestão apenas 2 opeções uma ligar/desligar de uma divisão ou da casa toda assim podes usar os meus meus de estado

        StringBuilder sb = new StringBuilder("-----------Fase de Mudança de Estado-----------\n\n");
        sb.append("1. Ligar/Desligar todos os dispositivos de uma casa \n");
        sb.append("2. Ligar/Desligar um dispositivos especifico de uma casa \n");
        sb.append("3. Ligar/Desligar todos os dispositivos de uma divisão de uma casa \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int energySupplierMenu() {
        StringBuilder sb = new StringBuilder("-----------Fornecedor de Energia-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int menuCasa() {
        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuAtualizarCasa() {

        // sugestão apenas escrever uma opção de dispositivos para assim apenas chameres a o meu menu de dispositivos.

        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar divisão \n");
        sb.append("2. Adicionar dispositivo \n"); // usar menu de adicionar dispositivos
        sb.append("3. Remover divisão\n");
        sb.append("4. Remover dispositivo \n"); // usar menu de remover dispositivos
        sb.append("5. Endereço \n");
        sb.append("6. Fornecedor de energia \n");
        sb.append("7. Propriatario \n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String MenuAddDivisão() {
        StringBuilder sb = new StringBuilder("   Adicionar Divisão   \n\n");
        sb.append("Nome: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}



