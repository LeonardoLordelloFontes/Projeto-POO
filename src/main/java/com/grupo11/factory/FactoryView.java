package com.grupo11.factory;

import com.grupo11.smartdevice.SmartDevice;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FactoryView {
        /**
         * Constantes dos menus
         *
         */
        private final String KEEP = "#";
        private final String[] ERROR = {"-1"};
        private final String[] BACK = {"*"};

    /**
     * Metodo que valida uma opeção numérica
     *
     * @param option número escrito
     * @param optionNumber o número de opeção máxima
     * @return a opeção se for válida e -1 se a opeção for invalida
     */

        private int isValidOption(int option, int optionNumber ){
            try {
                if (option < 1 || option > optionNumber) {
                    System.out.println("Opção inválida, digite um valor inteiro entre 1 e "+optionNumber);
                    return -1;
                }
                return option;
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, digite um valor inteiro entre 1 e"+optionNumber);
                return -1;
            }
        }

    /**
     * Metodo que valida o input dado pelo cliente é convertivel para double
     *
     * @param strNum input dado
     * @param propreti propriedade do dispositivo que o cliente intoduziu
     * @return true se for convertivel para double e false se não for.
     */
        private boolean isValidDouble(String strNum,String propreti) {

            boolean validacion = true;
            if (strNum == null) validacion  = false;
            else {
                try {
                    double d = Double.parseDouble(strNum);
                    if(d <= 0 ) validacion = false;
                } catch (NumberFormatException nfe) {
                    validacion = false;
                }
                if (validacion && Double.parseDouble(strNum) < 0) validacion = false;
            }
            if (!validacion) System.out.println("Opção inválida de"+ propreti +"digite um valor double não negativo!!");
            return validacion;
        }
     /**
      * * Metodo que valida o input dado pelo cliente é convertivel para integer
     *
     * @param strNum input dado
     * @param propreti propriedade do dispositivo que o cliente intoduziu
     * @return true se for convertivel para integer e false se não for.
     */
        private static boolean isValidInteger(String strNum, String propreti) {

            boolean validacion = true;
            if (strNum == null) validacion  = false;
            else {
                try {
                    int d = Integer.parseInt(strNum);
                    if(d <= 0 ) validacion = false;
                } catch (NumberFormatException nfe) {
                    validacion = false;
                }
                if (validacion && Integer.parseInt(strNum) < 0) validacion = false;
            }
            if (!validacion) System.out.println("Opção inválida de" +propreti+ "digite um valor double não negativo!!");
            return validacion;
        }

    /**
     * Menu das operações dos dispositivos
     * @return o numero da opeção se for válida ou -1 se não for
     */
    public int MenuTipoDispositivoOperacoes() {
            StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
            sb.append("1. Adicionar Dispositivo \n");
            sb.append("2. Remover Dispositivo \n");
            sb.append("3. Atualizar Dispositivo \n");
            sb.append("4. Listar Dispositivos\n");
            sb.append("5. Voltar \n\n");
            sb.append("Sua Opção (Selecionar Número): ");
            System.out.print(sb.toString());
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            return isValidOption(option,5);
        }

        /**
         * Menu das operações de adicionar dispositivos
         * @return o numero da opeção se for válida ou -1 se não for
         */
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

    /**
     * Menu de adicionar dispositivos geral
     * @return as componentes do dispositivo a ser criado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
        public String[] MenuDiviceAdd(){
            String[] input = new String[3];
            Scanner scanner = new Scanner(System.in);

            System.out.println("-----------Smart Device-----------\n\n");
            System.out.print("Se pretende voltar ao menu anterior escreva o simbolo [*] em algum local de escrita no terminal.\n");

            System.out.print("Código de Fábrica: ");
            input[0] = scanner.next();
            if (input[0].equals("*")) return this.BACK;

            System.out.print("Custo de Instalação: ");
            input[1] = scanner.next();
            if (input[1].equals("*")) return this.BACK;
            if( !isValidDouble(input[1], "Custo de Instalação") ) return this.ERROR;

            System.out.print("Quantidade de energia diária estática: ");
            input[2] = scanner.next();
            if (input[2].equals("*")) return this.BACK;
            if( !isValidDouble(input[2],"Quantidade de energia diária estática") ) return this.ERROR;

            return input;
        }

    /**
     * Menu de adicionar lampâmdas
     * @return as componentes do dispositivo a ser criado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
        public String[] MenuSmartBulbAdd() {
            String[] input = new String[4];
            Scanner scanner = new Scanner(System.in);

            String[] comumInput = MenuDiviceAdd();
            if(comumInput.equals(this.BACK)) return this.BACK;
            if( comumInput.equals(this.ERROR) ) return this.ERROR;

            System.arraycopy(comumInput,0,input,0,3);

            System.out.print("Dimensão(cm): ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return this.BACK;
            if( !isValidDouble(input[1],"Dimensão") ) return this.ERROR;

            return input;
        }

    /**
     * Menu de adicionar colunas
     * @return as componentes do dispositivo a ser criado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
        public  String[] MenuSmartSpeakerAdd() {
            String [] input = new String[5];
            Scanner scanner = new Scanner(System.in);

            String[] comumInput = MenuDiviceAdd();
            if(comumInput.equals(this.BACK)) return this.BACK;
            if( comumInput.equals(this.ERROR) ) return this.ERROR;

            System.arraycopy(comumInput,0,input,0,3);

            System.out.print("Marca: ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return this.BACK;

            System.out.print("Volume Máximo: ");
            input[4] = scanner.next();
            if (input[4].equals("*")) return this.BACK;
            if (!isValidInteger(input[4],"Volume Máximo")) return this.ERROR;

            return input;
        }

    /**
     * Menu de adicionar camaras
     * @return as componentes do dispositivo a ser criado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
        public String[] MenuSmartCamaraAdd() {
            String [] input = new String[5];
            Scanner scanner = new Scanner(System.in);

            String[] comumInput = MenuDiviceAdd();
            if(comumInput.equals(this.BACK)) return this.BACK;
            if( comumInput.equals(this.ERROR) ) return this.ERROR;

            System.arraycopy(comumInput,0,input,0,3);

            System.out.print("Resolução (pixeis): ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return this.BACK;
            if (!isValidInteger(input[3],"Resolução") ) return this.ERROR;

            System.out.print("Armazenamento (bytes): ");
            input[4] = scanner.next();
            if (input[4].equals("*")) return this.BACK;
            if (!isValidInteger(input[4],"Armazenamento") ) return this.ERROR;

            return input;
        }

    /**
     * Menu de remoção de dispositivos da fábrica
     * @return o dispositivo a ser removido ou this.BACK se for para ir para trás
     */
        public String MenuTipoDispositivoRemove(){
            String input = "*";
            Scanner scanner = new Scanner(System.in);
            System.out.print("-----------Remover Dispositivos-----------\n\n");

            System.out.print("Intruduza código de fabrica do dispositivo que pretende remover ou o simbolo[*] para voltar ao menu anterior: ");
            input = scanner.next();

            if (input.equals("*")) return this.BACK[0];

            return input;
        }

    /**
     * Menu de atualização de dispositivos da fábrica geral
     * @return as componentes do dispositivo a ser alterado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
    public String[] MenuDiviceUpdate(){

        String [] input = new String[4];
        Scanner scanner = new Scanner(System.in);
        String answer = "*";

        input[1] = this.KEEP;
        input[2] = this.KEEP;
        input[3] = this.KEEP;

        System.out.print("Intruduza código de fabrica do dispositivo que pretende alterar ou o simbolo [*] em qualquer local de escrita para voltar ao menu anterior: ");
        input[0] = scanner.next();
        if(input[0].equals("*")) return this.BACK;

        System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);
        if(answer.equals("*")) return this.BACK;
        if (answer.equals("Y")) {
            System.out.print(" Novo Código de Fábrica: ");
            input[1] = scanner.next();
            if(input[1].equals("*")) return this.BACK;}


        System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);
        if (answer.equals("*")) return this.BACK;
        if (answer.equals("Y")) {
            System.out.print(" Nova Quantidade de energia diária estatica : ");
            input[2] = scanner.next();
            if (input[2].equals("*")) return this.BACK;
            if ( !isValidDouble(input[2],"Quantidade de energia diária estatica") ) return this.ERROR;
        }

        System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);
        if(answer.equals("*")) return this.BACK;
        if (answer.equals("Y")) {
            System.out.print(" Novo Custo de instalação : ");
            input[3] = scanner.next();
            if (input[3].equals("*")) return this.BACK;
            if ( !isValidDouble(input[3],"Custo de instalação") ) return this.ERROR;
        }

        return input;
    }
    /**
     * Menu de atualização de lampâmdas da fábrica
     * @return as componentes do dispositivo a ser alterado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
        public String[] MenuSmartBulbUpdate() {
            String input[] = new String[2];
            String answer = "*";
            Scanner scanner = new Scanner(System.in);

            input[0] = this.KEEP;
            input[1] = this.KEEP;

            System.out.print("Deseja alterar a Dimensão(cm)?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Nova Dimensão(cm) : ");
                input[0] = scanner.next();
                if (input[1].equals("*")) return this.BACK;
                if ( !isValidDouble(input[0],"Dimensão") ) return this.ERROR;
            }

            System.out.print("Deseja alterar a Tonalidade ?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {

                StringBuilder sb = new StringBuilder("\n-----------Tipo de Tonalidades-----------\n");
                sb.append("1. Neutral \n");
                sb.append("2. Warm \n");
                sb.append("3. Cold \n");
                sb.append("Sua Opção (Selecionar Número): ");
                System.out.print(sb.toString());
                input[1] = scanner.next();

                if (input[1].equals("*")) return this.BACK;
                if ( !isValidInteger(input[1], "Tonalidade") &&  Integer.parseInt(input[1]) < 4 && Integer.parseInt(input[1]) > 0) return this.ERROR;
            }

            return input;
        }
    /**
     * Menu de atualização de coluna da fábrica
     * @return as componentes do dispositivo a ser alterado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */
        public String[] MenuSmartSpeakerUpdate() {
            String []input = new String[4];
            String answer = "*";
            Scanner scanner = new Scanner(System.in);

            input[0] = this.KEEP;
            input[1] = this.KEEP;
            input[2] = this.KEEP;
            input[3] = this.KEEP;

            System.out.print("Deseja alterar a Marca?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return this.BACK;

            if (answer.equals("Y")) {
                System.out.print("Nova Marca : ");
                input[0] = scanner.next();
                if (input[0].equals("*")) return this.BACK;
            }

            System.out.print("Deseja alterar o Volume Máximo?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return this.BACK;

            if (answer.equals("Y")) {
                System.out.print(" Novo Volume Máximo : ");
                input[1] = scanner.next();
                if (input[1].equals("*")) return this.BACK;
                if ( !isValidInteger(input[1],"Volume Máximo") ) return this.ERROR;
            }

            System.out.print("Deseja alterar o volume atual?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return this.BACK;

            if (answer.equals("Y")) {
                System.out.print(" Novo volume atual: ");
                input[2] = scanner.next();
                if (input[2].equals("*")) return this.BACK;
                if ( !isValidInteger(input[2],"volume") ) return this.ERROR;
            }

            System.out.print("Deseja alterar a estação de rádio?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return this.BACK;

            if (answer.equals("Y")) {
                System.out.print("Nova estação de rádio: ");
                input[3] = scanner.next();
                if (input[3].equals("*")) return this.BACK;
            }

            return input;
        }

    /**
     * Menu de atualização de camara da fábrica
     * @return as componentes do dispositivo a ser alterado ou this.BACK se for para ir para trás ou this.ERROR se o input do cliente for invalido
     */

        public String[] MenuSmartCamaraUpdate() {

            String input[] = new String[2];
            String answer = "N";
            Scanner scanner = new Scanner(System.in);

            input[0] = this.KEEP;
            input[1] = this.KEEP;

            System.out.print("Deseja alterar a resolução:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return this.BACK;
            if (answer.equals("Y")) {
                System.out.print(" Nova Resolução (pixeis): ");
                input[0] = scanner.next();
                if (input[0].equals("*")) return this.BACK;
                if ( !isValidInteger(input[0], "Resolução") ) return this.ERROR;
            }

            System.out.print("Deseja alterar o armazenamento:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if(answer.equals("*")) return this.BACK;
            if (answer.equals("Y")) {
                System.out.print(" Novo Armazenamento (bytes): ");
                input[1] = scanner.next();
                if (input[1].equals("*")) return this.BACK;
                if ( !isValidInteger(input[1],"Armazenamento") ) return this.ERROR;
            }

            return input;

        }

    /**
     * Menu que lista todos os dispositivos que se encontram na fábrica
     * @param listaDosTrue é lista todos os dispositivos que se encontram na fábrica
     */
    public void MenuListagem( List<SmartDevice> listaDosTrue){

            if(listaDosTrue.isEmpty()) System.out.println("Não foram criados dispositivos!!");

            else{
                for (SmartDevice sd: listaDosTrue) System.out.print("\n"+sd+"\n");
                System.out.println("\n\n");
            }

        }


}
