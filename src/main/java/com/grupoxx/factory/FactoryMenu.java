package com.grupoxx.factory;

import com.grupoxx.smartdevice.SmartDevice;
import com.grupoxx.smartdevice.SmartDeviceRepository;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class FactoryMenu {

        public static int MenuTipoDispositivoOperacoes() {
            StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
            sb.append("1. Adicionar Dispositivo \n");
            sb.append("2. Remover Dispositivo \n");
            sb.append("3. Atualizar Dispositivo \n");
            sb.append("4. Ligar e Desligar Dispositivos\n");
            sb.append("5. Alterar propriedes variaveis dos Dispositivos\n");
            sb.append("6. Voltar \n\n");
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

    public static int MenuSmartDiviceProperties(){
        StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
        sb.append("Qua o tipo de despositivo que pretende alterar as suas propriedades?\n");
        sb.append("1. Smart Bulb \n");
        sb.append("Cuja propriedade é a tonalidade do Smart Bulb\n");
        sb.append("2. Smart Speaker\n");
        sb.append("Cuja propriedade é o volume atual e a estação de rádio do Smart Speaker\n");
        sb.append("3. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();}

    public static int MenuSmartSpeakerProperties(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("------------Alterar propriedades em SmartSpeaker----------\n\n");

        StringBuilder sb = new StringBuilder();
        sb.append("1. Pretende alterar a estação de rádio para todos SmartSpeaker \n");
        sb.append("2. Pretende alterar o volume atual para todos SmartSpeaker \n");
        sb.append("3. Pretende alterar alguma das propriedades de SmartSpeaker expecificos \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());

        return scanner.nextInt();
    }

    public static  String[] MenuSmartSpeakerPropertiesEspecific(){
        String input[] = new String[3];
        String answer = "N";
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Smart Speaker Propriedades-----------\n\n");
        System.out.print("Intruduza código de fabrica do dispositivo que pretende alterar ou o simbolo [*] em qualquer local de escrita para voltar ao menu anterior: ");

        input[0] = scanner.next();
        if (input[0].equals("*")) return null;

        System.out.print("Deseja alterar o volume atual?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);
        if(answer.equals("*")) return null;

        if (answer.equals("Y")) {
            System.out.print(" Novo volume atual: ");
            input[1] = scanner.next();
            if (input[1].equals("*")) return null;
        }
        else {
            input[1] = "#";
        }

        System.out.print("Deseja alterar a estação de rádio?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);
        if(answer.equals("*")) return null;

        if (answer.equals("Y")) {
            System.out.print("Nova estação de rádio: ");
            input[2] = scanner.next();
            if (input[2].equals("*")) return null;
        }
        else {
            input[2] = "#";
        }
        return input;
    }


    public static String MenuSmartSpeakerVolume(){
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("-----------Alterar o Volume Atual de Todos os SmartSeaker-----------\n\n");

        System.out.print("Intruduza a novo volume atual de todos os SmartSeaker ou o simbolo[*] para voltar ao menu anterior: ");
        input = scanner.next();
        if (input.equals("*")) return null;

        return input;
    }

    public static String MenuSmartSpeakerRadioStation(){
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("-----------Alterar a Estação de Rádio de Todos os SmartSeaker-----------\n\n");

        System.out.print("Intruduza a nova estação de rádio de todos os SmartSeaker ou o simbolo[*] para voltar ao menu anterior: ");
        input = scanner.next();
        if (input.equals("*")) return null;

        return input;
    }

    public static int MenuSmartBulbToneChange(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("------------Alterar Tonalidade em SmartBulb----------\n\n");

        StringBuilder sb = new StringBuilder();
        sb.append("1. Pretende por a tonalidade Neutral para todos SmartBulb \n");
        sb.append("2. Pretende por a tonalidade  Warm para todos SmartBulb\n");
        sb.append("3. Pretende por a tonalidade  Cold para todos SmartBulb\n");
        sb.append("4. Pretende alterar a tonalidade de SmartBulb expecificos \n");
        sb.append("5. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());

        return scanner.nextInt();
        }

    public static String[] MenuSmartBulbEspecificToneChange(){
        String input[] = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("-----------Alterar a Tonalidade do SmartBulb -----------\n\n");

        System.out.print("Intruduza código de fabrica do SmartBulb que pretende alterar a Tonalidade ou o simbolo[*] para voltar ao menu anterior: ");
        input[0] = scanner.next();
        if (input[0].equals("*")) return null;

        System.out.println("Intruduza a Tonalidade que predende que o seu dispositivo tenha ou o simbolo[*] para voltar ao menu anterior: ");

        StringBuilder sb = new StringBuilder("-----------Tipo de Tonalidades-----------\n\n");
        sb.append("N. Neutral \n");
        sb.append("W. Warm \n");
        sb.append("C. Cold \n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        input[1] = scanner.next();
        if (input[1].equals("*")) return null;

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

        public static int MenuSmartDeviceState(){

            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------ligar e Desligar Dispositivos-----------\n\n");

            StringBuilder sb = new StringBuilder();
            sb.append("1. Pretende ligar ou Desligar todos Smart Bulb \n");
            sb.append("2. Pretende ligar ou Desligar todos Smart Speaker\n");
            sb.append("3. Pretende ligar ou Desligar tosos Smart Camara \n");
            sb.append("4. Pretende ligar ou Desligar todos os dispositivos \n");
            sb.append("5. Pretende ligar ou Desligar dispositivos expecificos \n");
            sb.append("6. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());

            return scanner.nextInt();
        }

        public static int MenuSmartDeviceOffAndOn(){

            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------ligar ou Desligar-----------\n\n");

            StringBuilder sb = new StringBuilder();
            sb.append("1. Pretende ligar \n");
            sb.append("2. Pretende Desligar\n");
            sb.append("3. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());

            return scanner.nextInt();
        }
        public static String SmartDeviceEspecificOffAndOn(){
            String input = "N";
            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------Ligar ou Desligar Dispositivos-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende ligar ou desligar oo simbolo[*] para voltar ao menu anterior: ");
            input = scanner.next();

            if (input.equals("*")) return null;

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
