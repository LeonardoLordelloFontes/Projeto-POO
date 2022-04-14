package com.grupoxx.smartdevice;

import com.grupoxx.smarthouse.SmartHouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class SmartDeviceMenu {

        public static int MenuTipoDispositivoOperacoes() {
            StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
            sb.append("1. Adicionar Dispositivo \n");
            sb.append("2. Remover Dispositivo \n");
            sb.append("3. Atualizar Dispositivo \n");
            sb.append("4. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }
        public static int MenuTipoDispositivoAdd() {
            StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
            sb.append("1. Adicionar  SmartBulb \n");
            sb.append("2. Adicionar  SmartSpeaker\n");
            sb.append("3. Adicionar  SmartCamera\n");
            sb.append("4. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }
        public static String[] MenuSmartBulbAdd() {
            String input[] = new String[4];
            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------Smart Bulb-----------\n\n");
            System.out.print("Se pretende voltar ao menu anterior escreva o simbolo [*] em algum local de escrita no terminal.\n");

            System.out.print("Código de Fábrica: ");
            input[0] = scanner.next();
            if (input[0].equals("*")) return null;

            System.out.print("Dimensão(cm): ");
            input[1] = scanner.next();
            if (input[1].equals("*")) return null;

            System.out.print("Custo de Instalação:");
            input[2] = scanner.next();
            if (input[2].equals("*")) return null;

            System.out.print("Quantidade de energia diária gasta:");
            input[3] = scanner.next();
            if (input[3].equals("*")) return null;

            return input;
        }
        public static String[] MenuSmartSpeakerAdd() {
            String input[] = new String[5];
            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------Smart Speaker-----------\n\n");
            System.out.print("Se pretende voltar para o menu anterior escreva o simbolo [*] em algum local de escrita no terminal.\n");

            System.out.print("Código de Fábrica: ");
            input[0] = scanner.next();
            if (input[0].equals("*")) return null;

            System.out.print("Custo de Instalação: ");
            input[1] = scanner.next();
            if (input[1].equals("*")) return null;

            System.out.print("Quantidade de energia diária gasta: ");
            input[2] = scanner.next();
            if (input[2].equals("*")) return null;

            System.out.print("Marca: ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return null;

            System.out.print("Volume Máximo: \n");
            input[4] = scanner.next();
            if (input[4].equals("*")) return null;

            return input;
        }
        public static String[] MenuSmartCamaraAdd() {
            String input[] = new String[5];
            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------Smart Camara-----------\n\n");
            System.out.print("Se pretende voltar para o menu anterior escreva o simbolo [*] em algum local de escrita no terminal.\n");

            System.out.print("Código de Fábrica: ");
            input[0] = scanner.next();
            if (input[0].equals("*")) return null;

            System.out.print("Custo de Instalação: ");
            input[1] =scanner.next();
            if (input[1].equals("*")) return null;

            System.out.print("Quantidade de energia diária gasta: ");
            input[2] = scanner.next();
            if (input[2].equals("*")) return null;

            System.out.print("Resolução (pixeis): ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return null;

            System.out.print("Armazenamento (bytes): ");
            input[4] = scanner.next();
            if (input[4].equals("*")) return null;

            return input;
        }
        public static String MenuTipoDispositivoRemove(){
            String input = "N";
            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------Remover Dispositivos-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende remover ou o simbolo[*] para voltar ao menu anterior: ");
            input = scanner.next();

            if (input.equals("*")) return null;

            return input;
        }


        public static int MenuTipoDispositivoUpdate() {
            StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
            sb.append("Qua o tipo de despositivo que pretende alterar?\n");
            sb.append("1. Smart Bulb \n");
            sb.append("2. Smart Speaker\n");
            sb.append("3. Smart Camara \n");
            sb.append("4. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }

        public static String[] MenuSmartBulbUpdate() {
            String input[] = new String[5];
            String answer = "N";
            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------Smart Bulb-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende alterar ou o simbolo [*] em qualquer local de escrita para voltar ao menu anterior: ");
            input[0] = scanner.next();

            if(input[0].equals("*")) return null;

            System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Código de Fábrica: ");
                input[1] = scanner.next();
                if(input[1].equals("*")) return null;}
            else {
                input[1] = "#";
                }

            System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Nova Quantidade de energia diária estatica : ");
                input[2] = scanner.next();
                if (input[2].equals("*")) return null;
            } else {
                input[2] = "#";
            }

            System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Custo de instalação : ");
                input[3] = scanner.next();
                if (input[3].equals("*")) return null;
            } else {
                input[3] = "#";
            }

            System.out.print("Deseja alterar a Dimensão(cm)?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print("Nova Dimensão(cm) : ");
                input[4] = scanner.next();
                if (input[4].equals("*")) return null;
            } else {
                input[4] = "#";
            }

            return input;
        }
        public static String[] MenuSmartSpeakerUpdate() {
            String input[] = new String[6];
            String answer = "N";
            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------Smart Speaker-----------\n\n");
            System.out.print("Intruduza código de fabrica do dispositivo que pretende alterar ou o simbolo [*] em qualquer local de escrita para voltar ao menu anterior: ");

            input[0] = scanner.next();
            if (input[0].equals("*")) return null;

            System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Código de Fábrica: ");
                input[1] = scanner.next();
                if (input[1].equals("*")) return null;
            }
            else {
                input[1] = "#";
            }

            System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print("Novo Quantidade de energia diária gasta : ");
                input[2] = scanner.next();
                if (input[2].equals("*")) return null;
            }
            else {
                input[2] = "#";
            }

            System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Custo de instalação : ");
                input[3] = scanner.next();
                if (input[3].equals("*")) return null;
            }
            else {
                input[3] = "#";
            }

            System.out.print("Deseja alterar a Marca?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print("Nova Marca : ");
                input[4] = scanner.next();
                if (input[4].equals("*")) return null;
            }
            else {
                input[4] = "#";
            }

            System.out.print("Deseja alterar o Volume Máximo?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Volume Máximo : ");
                input[5] = scanner.next();
                if (input[5].equals("*")) return null;
            }
            else {
                input[5] = "#";
            }

            return input;
        }
        public static String[] MenuSmartCamaraUpdate() {

            String input[] = new String[6];
            String answer = "N";
            Scanner scanner = new Scanner(System.in);
            System.out.println("-----------Smart Speaker-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende alterar ou o simbolo [*] em qualquer local de escrita para voltar ao menu anterior: ");
            input[0] = scanner.next();

            System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Código de Fábrica: ");
                input[1] = scanner.next();
                if (input[1].equals("*")) return null;
            }
            else {
                input[1] = "#";
            }

            System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print("Nova Quantidade de energia diária gasta : ");
                input[2] = scanner.next();
                if (input[2].equals("*")) return null;
            }
            else {
                input[2] = "#";
            }

            System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Custo de instalação : ");
                input[3] = scanner.next();
                if (input[3].equals("*")) return null;
            }
            else {
                input[3] = "#";
            }

            System.out.print("Deseja alterar a resolução:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Nova Resolução (pixeis): ");
                input[4] = scanner.next();
                if (input[4].equals("*")) return null;
            }
            else {
                input[4] = "#";
            }

            System.out.print("Deseja alterar o armazenamento:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Armazenamento (bytes): ");
                input[5] = scanner.next();
                if (input[5].equals("*")) return null;
            }
            else {
                input[5] = "#";
            }

            return input;

        }

    public static String smartHouseSelectSmartDeviceMenu(SmartDeviceRepository smartDeviceRepository) {
        List<SmartDevice> smartDevices = smartDeviceRepository.findAllSmartDevices();
        StringBuilder sb = new StringBuilder("-----------Selecionar Dispositivo-----------\n\n");
        smartDevices.forEach(smartDevice -> sb.append(smartDevice).append("\n"));
        sb.append("Para cancelar a ação digite *\n");
        sb.append("Selecione o dispositivo (pelo código do dispositivo): ");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}
