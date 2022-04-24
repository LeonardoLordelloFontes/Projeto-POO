package com.grupoxx.datastatus;

import com.grupoxx.simulation.Invoicer;
import com.grupoxx.smartdevice.SmartDevice;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DataStatusMenu {
    private static final String BACK = "*";

    private int isValidOption(int option, int optionNumber) {
        try {
            if (option < 1 || option > optionNumber) {
                System.out.println("Opção inválida, digite um valor inteiro entre 1 e " + optionNumber);
                return -1;
            }
            return option;
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida, digite um valor inteiro entre 1 e 5");
            return -1;
        }
    }

    public int MenuEstatistica() {
        StringBuilder sb = new StringBuilder("-----------Estatistica-----------\n\n");
        sb.append("1.Qual é a casa que mais gastou no periúdo da simulação\n");
        sb.append("2.Qual o comercializador com maior volume de facturação  \n");
        sb.append("3.Listar as facturas emitidas por um comercializador  \n");
        sb.append("4.Dar uma ordenação dos maiores consumidores de energia durante o períıodo de simulação\n");
        sb.append("5. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        return isValidOption(option, 5);
    }

    public String MenuListaFaturasDoComercializador() {
        String input = "*";
        Scanner scanner = new Scanner(System.in);
        System.out.print("----------- Lista Faturas Do Comercializador -----------\n\n");

        System.out.print("Intruduza o nome do comercializador de energia que pretende listar as faturas ou o simbolo[*] para voltar ao menu anterior: ");
        input = scanner.next();

        if (input.equals("*")) return this.BACK;

        return input;
    }


    public void MenuResultados(String res, int r) {

        switch (r) {
            case 1:
                System.out.println("A casa que mais gastou no períudo da simulação tem o endereço " + res);
            case 2:
                System.out.println("o comercializador com maior volume de facturação foi o " + res);
        }

    }

    public void MenuListagemDosMaioresConsumidores(List<String> listaDosMaioresConsumidores) {

        if (listaDosMaioresConsumidores.isEmpty()) System.out.println("Não extistem comsumidores!!");

        else {
            for (String s : listaDosMaioresConsumidores) System.out.print(s +"\n");
        }

    }

    public void MenuListagemDasFaturas(List<Invoicer> listaDasFaturas) {

        if (listaDasFaturas.isEmpty()) System.out.println("Não extistem comsumidores!!");

        else {
            for (Invoicer i : listaDasFaturas) System.out.print(i +"\n");
        }

    }


}