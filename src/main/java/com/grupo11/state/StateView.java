package com.grupo11.state;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StateView {
    public int stateMenu() {
        System.out.print( """
                -----------Estado-----------
                
                1. Guardar Estado
                2. Carregar Estado
                3. Voltar
                
                Sua opção (Selecione um número):\s""");
        Scanner scanner = new Scanner(System.in);
        String s = "Opção inválida, digite um valor inteiro entre 1 e 3";
        try {
            int option = scanner.nextInt();
            if (option < 1 || option > 3) {
                System.out.println(s);
                return -1;
            }
            return option;
        } catch (InputMismatchException e) {
            System.out.println(s);
            return -1;
        }
    }

    public String saveStateMenu() {
        System.out.print("Insira o caminho onde deseja guardar o estado do programa (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("*")) return null;
        return input;
    }

    public String loadStateMenu() {
        System.out.print("Insira o caminho onde está o estado que deseja carregar (para cancelar digite *): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("*")) return null;
        return input;
    }
}
