package com.grupoxx.factory;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class FactoryMenu {

        private final String[] error = {"-1"};

        public String[] getError(){

            return this.error.clone();
        }

        private int isValidOption(int option, int optionNumber ){
            try {
                if (option < 1 || option > optionNumber) {
                    System.out.println("Opção inválida, digite um valor inteiro entre 1 e 5");
                    return -1;
                }
                return option;
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, digite um valor inteiro entre 1 e 5");
                return -1;
            }

        }

        private boolean isValidDouble(String strNum) {

            boolean validacion = true;
            if (strNum == null) validacion  = false;
            else {
                try {
                    double d = Double.parseDouble(strNum);
                } catch (NumberFormatException nfe) {
                    validacion = false;
                }
                if (validacion && Double.parseDouble(strNum) < 0) validacion = false;
            }
            if (!validacion) System.out.println("Opção inválida, digite um valor double não negativo!!");
            return validacion;
        }

        private static boolean isValidInteger(String strNum) {

            boolean validacion = true;
            if (strNum == null) validacion  = false;
            else {
                try {
                    int d = Integer.parseInt(strNum);
                } catch (NumberFormatException nfe) {
                    validacion = false;
                }
                if (validacion && Integer.parseInt(strNum) < 0) validacion = false;
            }
            if (!validacion) System.out.println("Opção inválida, digite um valor double não negativo!!");
            return validacion;
        }

        public int MenuTipoDispositivoOperacoes() {
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
            int option = scanner.nextInt();
            return isValidOption(option,6);
        }

        public int MenuTipoDispositivoAdd() {
            StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
            sb.append("1. Adicionar  SmartBulb \n");
            sb.append("2. Adicionar  SmartSpeaker\n");
            sb.append("3. Adicionar  SmartCamera\n");
            sb.append("4. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            return isValidOption(option,4);
        }

        public String[] MenuDiviceAdd(){
            String[] input = new String[3];
            Scanner scanner = new Scanner(System.in);

            System.out.println("-----------Smart Device-----------\n\n");
            System.out.print("Se pretende voltar ao menu anterior escreva o simbolo [*] em algum local de escrita no terminal.\n");

            System.out.print("Código de Fábrica: ");
            input[0] = scanner.next();
            if (input[0].equals("*")) return null;

            System.out.print("Custo de Instalação:");
            input[1] = scanner.next();
            if (input[1].equals("*")) return null;
            if( !isValidDouble(input[1]) ) return this.error;

            System.out.print("Quantidade de energia diária gasta:");
            input[2] = scanner.next();
            if (input[2].equals("*")) return null;
            if( !isValidDouble(input[2]) ) return this.error;

            return input;
        }


        public String[] MenuSmartBulbAdd() {
            String[] input = new String[4];
            Scanner scanner = new Scanner(System.in);

            String[] comumInput = MenuDiviceAdd();
            if(comumInput == null) return null;
            if( comumInput.equals(this.error) ) return this.error;

            System.arraycopy(comumInput,0,input,0,3);

            System.out.print("Dimensão(cm): ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return null;
            if( !isValidDouble(input[1]) ) return this.error;

            return input;
        }

        public  String[] MenuSmartSpeakerAdd() {
            String [] input = new String[5];
            Scanner scanner = new Scanner(System.in);

            String[] comumInput = MenuDiviceAdd();
            if(comumInput == null) return null;
            if( comumInput.equals(this.error) ) return this.error;

            System.arraycopy(comumInput,0,input,0,3);

            System.out.print("Marca: ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return null;

            System.out.print("Volume Máximo: \n");
            input[4] = scanner.next();
            if (input[4].equals("*")) return null;
            if (!isValidInteger(input[4]) ) return this.error;

            return input;
        }
        public String[] MenuSmartCamaraAdd() {
            String [] input = new String[5];
            Scanner scanner = new Scanner(System.in);

            String[] comumInput = MenuDiviceAdd();
            if(comumInput == null) return null;
            if( comumInput.equals(this.error) ) return this.error;

            System.arraycopy(comumInput,0,input,0,3);

            System.out.print("Resolução (pixeis): ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return null;
            if (!isValidInteger(input[3]) ) return this.error;

            System.out.print("Armazenamento (bytes): ");
            input[4] = scanner.next();
            if (input[4].equals("*")) return null;
            if (!isValidInteger(input[4]) ) return this.error;

            return input;
        }
        public String MenuTipoDispositivoRemove(){
            String input = "*";
            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------Remover Dispositivos-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende remover ou o simbolo[*] para voltar ao menu anterior: ");
            input = scanner.next();

            if (input.equals("*")) return null;

            return input;
        }

    public int MenuSmartDiviceProperties(){
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
        int option = scanner.nextInt();
        return isValidOption(option,3);
        }

    public int MenuSmartSpeakerProperties(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("------------Alterar propriedades em SmartSpeaker----------\n\n");

        StringBuilder sb = new StringBuilder();
        sb.append("1. Pretende alterar a estação de rádio para todos SmartSpeaker \n");
        sb.append("2. Pretende alterar o volume atual para todos SmartSpeaker \n");
        sb.append("3. Pretende alterar alguma das propriedades de SmartSpeaker expecificos \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        int option = scanner.nextInt();
        return isValidOption(option,4);
    }

    public String[] MenuSmartSpeakerPropertiesEspecific(){
        String []input = new String[3];
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


    public String MenuSmartSpeakerVolume(){
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("-----------Alterar o Volume Atual de Todos os SmartSeaker-----------\n\n");

        System.out.print("Intruduza a novo volume atual de todos os SmartSeaker ou o simbolo[*] para voltar ao menu anterior: ");
        input = scanner.next();
        if (input.equals("*")) return null;

        return input;
    }

    public String MenuSmartSpeakerRadioStation(){
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("-----------Alterar a Estação de Rádio de Todos os SmartSeaker-----------\n\n");

        System.out.print("Intruduza a nova estação de rádio de todos os SmartSeaker ou o simbolo[*] para voltar ao menu anterior: ");
        input = scanner.next();
        if (input.equals("*")) return null;

        return input;
    }

    public int MenuSmartBulbToneChange(){

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
        int option = scanner.nextInt();
        return isValidOption(option,5);
        }

    public String[] MenuSmartBulbEspecificToneChange(){
        String []input = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("-----------Alterar a Tonalidade do SmartBulb -----------\n\n");

        System.out.print("Intruduza código de fabrica do SmartBulb que pretende alterar a Tonalidade ou o simbolo[*] para voltar ao menu anterior: ");
        input[0] = scanner.next();
        if (input[0].equals("*")) return null;

        System.out.println("Intruduza a Tonalidade que predende que o seu dispositivo tenha ou o simbolo[*] para voltar ao menu anterior: ");

        StringBuilder sb = new StringBuilder("-----------Tipo de Tonalidades-----------\n\n");
        sb.append("1. Neutral \n");
        sb.append("2. Warm \n");
        sb.append("3. Cold \n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        input[1] = scanner.next();
        if (input[1].equals("*")) return null;

        return input;
    }

    public String[] MenuDiviceUpdate(){

        String [] input = new String[4];
        Scanner scanner = new Scanner(System.in);
        String answer = "*";

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
            if ( !isValidDouble(input[2]) ) return this.error;
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
            if ( !isValidDouble(input[3]) ) return this.error;
        } else {
            input[3] = "#";
        }

        return input;
    }

        public String MenuSmartBulbUpdate() {
            String input;
            String answer = "*";
            Scanner scanner = new Scanner(System.in);

            System.out.print("Deseja alterar a Dimensão(cm)?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print("Nova Dimensão(cm) : ");
                input = scanner.next();
                if (input.equals("*")) return null;
                if ( !isValidDouble(input) ) return "-1";
            } else {
                input = "#";
            }

            return input;
        }
        public String[] MenuSmartSpeakerUpdate() {
            String []input = new String[2];
            String answer = "*";
            Scanner scanner = new Scanner(System.in);

            System.out.print("Deseja alterar a Marca?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print("Nova Marca : ");
                input[0] = scanner.next();
                if (input[0].equals("*")) return null;
            }
            else {
                input[0] = "#";
            }

            System.out.print("Deseja alterar o Volume Máximo?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Volume Máximo : ");
                input[1] = scanner.next();
                if (input[1].equals("*")) return null;
                if ( !isValidInteger(input[1]) ) return this.error;
            }
            else {
                input[1] = "#";
            }

            return input;
        }
        public String[] MenuSmartCamaraUpdate() {

            String input[] = new String[2];
            String answer = "N";
            Scanner scanner = new Scanner(System.in);

            System.out.print("Deseja alterar a resolução:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Nova Resolução (pixeis): ");
                input[0] = scanner.next();
                if (input[0].equals("*")) return null;
                if ( !isValidInteger(input[0]) ) return this.error;
            }
            else {
                input[0] = "#";
            }

            System.out.print("Deseja alterar o armazenamento:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return null;

            if (answer.equals("Y")) {
                System.out.print(" Novo Armazenamento (bytes): ");
                input[1] = scanner.next();
                if (input[1].equals("*")) return null;
                if ( !isValidInteger(input[1]) ) return this.error;
            }
            else {
                input[1] = "#";
            }

            return input;

        }

        public int MenuSmartDeviceState(){

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
            int option = scanner.nextInt();
            return isValidOption(option,6);
        }

        public int MenuSmartDeviceOffAndOn(){

            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------ligar ou Desligar-----------\n\n");

            StringBuilder sb = new StringBuilder();
            sb.append("1. Pretende ligar \n");
            sb.append("2. Pretende Desligar\n");
            sb.append("3. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());
            int option = scanner.nextInt();
            return isValidOption(option,3);
        }

        public String SmartDeviceEspecificOffAndOn(){
            String input = "N";
            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------Ligar ou Desligar Dispositivos-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende ligar ou desligar oo simbolo[*] para voltar ao menu anterior: ");
            input = scanner.next();

            if (input.equals("*")) return null;

            return input;
    }

}
