package com.grupo11.datastatus;

import com.grupo11.simulation.Invoicer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DataStatusView {

    /**
     * Constante de ir para trás
     */

    private static final String BACK = "*";

    /**
     * Metodo que valida uma opeção numérica
     *
     * @param option número escrito
     * @param optionNumber o número de opção máxima
     * @return a opeção se for válida e -1 se a opeção for invalida
     */

    private int isValidOption(int option, int optionNumber) {
        try {
            if (option < 1 || option > optionNumber) {
                System.out.println("Opção inválida, digite um valor inteiro entre 1 e " + optionNumber);
                return -1;
            }
            return option;
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida, digite um valor inteiro entre 1 e " + optionNumber);
            return -1;
        }
    }

    /**
     * Menu de estatistica
     *
     * @return a opção que o usuario escolher
     */

    public int menuDataStatus() {
        StringBuilder sb = new StringBuilder("-----------Estatistica-----------\n\n");
        sb.append("1. Qual é a casa que mais gastou no período da simulação\n");
        sb.append("2. Qual o comercializador com maior volume de facturação  \n");
        sb.append("3. Listar as facturas emitidas por um comercializador  \n");
        sb.append("4. Dar uma ordenação dos maiores consumidores de energia durante o período de simulação\n");
        sb.append("5. Voltar ao menu inicial \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb);
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        return isValidOption(option, 5);
    }

    /**
     * Menu de listagem de faturas
     *
     * @return o nome do Comercializador ou *, caso o utilizador deseje voltar
     */

    public String menuListSupplierInvoicers() {
        String input = "*";
        Scanner scanner = new Scanner(System.in);
        System.out.print("----------- Lista Faturas Do Comercializador -----------\n\n");

        System.out.print("Intruduza o nome do comercializador de energia que pretende listar as faturas ou o simbolo[*] para voltar ao menu anterior: ");
        input = scanner.next();

        if (input.equals("*")) return BACK;

        return input;
    }

    /**
     * Menu de resultados
     *
     * @param res resultado do calculo
     * @param r escolha do tipo de calculo
     */

    public void menuResults(String res, int r) {
        switch (r) {
            case 1:
                System.out.println("A casa que mais gastou no período da simulação tem o endereço " + res);
            case 2:
                System.out.println("o comercializador com maior volume de facturação foi o " + res);
        }
    }

    /**
     * Menu de listagem dos maiores consumidores
     *
     * @param largestConsumers lista dos maiores consumidores
     */

    public void menuListLargestConsumers(List<String> largestConsumers) {
        if (largestConsumers.isEmpty()) System.out.println("Não existem comsumidores!!");
        else {
            for (String s : largestConsumers) System.out.print(s +"\n");
        }
    }

    /**
     *
     * @param invoicers
     */

    public void menuListAllInvoicers(List<Invoicer> invoicers) {
        if (invoicers.isEmpty()) System.out.println("Não existem comsumidores!!");
        else {
            for (Invoicer i : invoicers) System.out.print(i +"\n");
        }
    }
}