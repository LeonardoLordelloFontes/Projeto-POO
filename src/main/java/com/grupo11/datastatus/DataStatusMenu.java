package com.grupo11.datastatus;

import com.grupo11.simulation.Invoicer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DataStatusMenu {
    /**
     * Constante de ir para trás
     */
    private static final String BACK = "*";

    /**
     * Metodo que valida uma opeção numérica
     *
     * @param option número escrito
     * @param optionNumber o número de opeção máxima
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
     * @return a opeção que o usuario escolher
     */
    public int MenuEstatistica() {
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
     * @return  a opeção do Comercializador
     */
    public String MenuListaFaturasDoComercializador() {
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
    public void MenuResultados(String res, int r) {

        switch (r) {
            case 1:
                System.out.println("A casa que mais gastou no períudo da simulação tem o endereço " + res);
            case 2:
                System.out.println("o comercializador com maior volume de facturação foi o " + res);
        }

    }

    /**
     * Menu de listagem dos maiores consumidores
     *
     * @param listaDosMaioresConsumidores lista dos maiores consumidores
     */
    public void MenuListagemDosMaioresConsumidores(List<String> listaDosMaioresConsumidores) {

        if (listaDosMaioresConsumidores.isEmpty()) System.out.println("Não extistem comsumidores!!");

        else {
            for (String s : listaDosMaioresConsumidores) System.out.print(s +"\n");
        }

    }

    /**
     *
     * @param listaDasFaturas
     */
    public void MenuListagemDasFaturas(List<Invoicer> listaDasFaturas) {

        if (listaDasFaturas.isEmpty()) System.out.println("Não extistem comsumidores!!");

        else {
            for (Invoicer i : listaDasFaturas) System.out.print(i +"\n");
        }

    }


}