package com.grupoxx.menu;
import java.util.*;


public abstract class Menu {

    public static int menuInicial() {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU INICIAL-----------\n\n");
        sb.append("1. Simulação Manual \n");
        sb.append("2. Simulação Automática \n");
        sb.append("3. Carregar Estado \n");
        sb.append("4. Guardar Estado \n");
        sb.append("5. Sair \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

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
    public static int menuFasedeConfiguracao() {
        StringBuilder sb = new StringBuilder("-----------Fase de Configuração-----------\n\n");
        sb.append("1. Configurar fornecedores de energia \n");
        sb.append("2. Configurar casas \n");
        sb.append("3. Configurar dispositivos \n");
        sb.append("4. Listar foncedores de energia \n");
        sb.append("5. Listar casas \n");
        sb.append("6. Listar dispositivos \n");
        sb.append("7. Carregar ficheiro com configurações \n");
        sb.append("8. Salvar configurações atuais para um ficheiro \n");
        sb.append("9. Próxima etapa \n");
        sb.append("10. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuMudançaDeEstado() {
        StringBuilder sb = new StringBuilder("-----------Fase de Mudança de Estado-----------\n\n");
        sb.append("1. Ligar/Desligar todos os dispositivos de uma casa \n");
        sb.append("2. Ligar/Desligar um dispositivos especifico de uma casa \n");
        sb.append("3. Ligar/Desligar todos os dispositivos de uma divisão de uma casa \n");
        sb.append("4. Iniciar Simulação \n");
        sb.append("5. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuFornecedordeEnergia() {
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
        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar divisão \n");
        sb.append("2. Adicionar dispositivo \n");
        sb.append("3. Remover divisão\n");
        sb.append("4. Remover dispositivo \n");
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
    public static String[] MenuAddOwner() {
        String input[] = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("   Propriatário   \n\n");
        System.out.print("Se pretende voltar atrás escreva a letra [N] em algum local de escrita no terminal.\n");

        System.out.print("NIF: ");
        input[0] = scanner.next();
        if (input[0].equals("N")) return null;

        scanner.nextLine();
        System.out.print("Nome: ");
        input[1] = scanner.nextLine();
        if (input[1].equals("N")) return null;

        return input;
    }
    public static int MenuDispositivos() {
        StringBuilder sb = new StringBuilder("-----------Dispositivos-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.print(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String[] AdicionarFornecedordeEnergia() {
        String[] input = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("-----------Adicionar um Fornecedor de Energia-----------\n\n");
        System.out.print("Se pretende voltar atrás escreva a letra [N] em algum local de escrita no terminal.\n");

        System.out.print("Nome: ");
        input[0] = scanner.next();
        if (input[0].equals("N")) return null;

        scanner.nextLine();
        System.out.print("Fórmula: ");
        input[1] = scanner.nextLine();
        if (input[1].equals("N")) return null;

        return input;
    }
    public static String[] AtualizarFornecedordeEnergia() {
        String[] input = new String[3];
        Scanner scanner = new Scanner(System.in);
        String answer = "N";
        System.out.println("-----------Atualizar um Fornecedor de Energia-----------\n\n");

        System.out.print("Intruduza o nome atual do fornecedor de enrgia que pretende alterar ou a letra [N] para voltar para o menu anterior: ");
        input[0] = scanner.next();

        if( !input[0].equals("N") ){

            System.out.print("Deseja alterar o nome do fornecedor?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Nome: ");
                input[1] = scanner.next();
            } else {
                input[1] = "#";
            }

            System.out.print("Deseja alterar a formula do fornecedor?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                scanner.nextLine();
                System.out.print("Fórmula: ");
                input[2] = scanner.nextLine();
            } else {
                input[2] = "#";
            }
        }

        System.out.print("Voltar para o menu anterior?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);

        if (answer.equals("N") && !input[0].equals("N")) return input;

        else return null;
    }
    public static int MenuTipoDispositivo() {
        StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
        sb.append("1. Smart Bulb \n");
        sb.append("2. Smart Speaker\n");
        sb.append("3. Smart Camara \n");
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
        System.out.print("Se pretende voltar atrás escreva a letra [N] em algum local de escrita no terminal.\n");

        System.out.print("Código de Fábrica: ");
        input[0] = scanner.next();
        if (input[0].equals("N")) return null;

        System.out.print("Dimensão(cm): ");
        input[1] = scanner.next();
        if (input[1].equals("N")) return null;

        System.out.print("Custo de Instalação:");
        input[2] = scanner.next();
        if (input[2].equals("N")) return null;

        System.out.print("Quantidade de energia diária gasta:");
        input[3] = scanner.next();
        if (input[3].equals("N")) return null;

        return input;
    }
    public static String[] MenuSmartSpeakerAdd() {
        String input[] = new String[5];
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Smart Speaker-----------\n\n");
        System.out.print("Se pretende voltar atrás escreva a letra [N] em algum local de escrita no terminal.\n");

        System.out.print("Código de Fábrica: ");
        input[0] = scanner.next();
        if (input[0].equals("N")) return null;

        System.out.print("Custo de Instalação: ");
        input[1] = scanner.next();
        if (input[1].equals("N")) return null;

        System.out.print("Quantidade de energia diária gasta: ");
        input[2] = scanner.next();
        if (input[2].equals("N")) return null;

        System.out.print("Marca: ");
        input[3] = scanner.next();
        if (input[3].equals("N")) return null;

        System.out.print("Volume Máximo: \n");
        input[4] = scanner.next();
        if (input[4].equals("N")) return null;

        return input;
    }
    public static String[] MenuSmartCamaraAdd() {
        String input[] = new String[5];
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Smart Camara-----------\n\n");
        System.out.print("Se pretende voltar atrás escreva a letra [N] em algum local de escrita no terminal.\n");

        System.out.print("Código de Fábrica: ");
        input[0] = scanner.next();
        if (input[0].equals("N")) return null;

        System.out.print("Custo de Instalação: ");
        input[1] =scanner.next();
        if (input[1].equals("N")) return null;

        System.out.print("Quantidade de energia diária gasta: ");
        input[2] = scanner.next();
        if (input[2].equals("N")) return null;

        System.out.print("Resolução (pixeis): ");
        input[3] = scanner.next();
        if (input[3].equals("N")) return null;

        System.out.print("Armazenamento (bytes): ");
        input[4] = scanner.next();
        if (input[4].equals("N")) return null;

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

        System.out.print("Intruduza código de fabrica do dispositivo que pretende alterar ou a letra [N] para voltar para atrás: ");
        input[0] = scanner.next();

        if(!input[0].equals("N")) {
            System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print(" Código de Fábrica: ");
                input[1] = scanner.next();
            } else {
                input[1] = "#";
            }

            System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Quantidade de energia diária gasta : ");
                input[2] = scanner.next();
            } else {
                input[2] = "#";
            }

            System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Custo de instalação : ");
                input[3] = scanner.next();
            } else {
                input[3] = "#";
            }

            System.out.print("Deseja alterar a Dimensão(cm)?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Dimensão(cm) : ");
                input[4] = scanner.next();
            } else {
                input[4] = "#";
            }
        }

        System.out.print("Voltar para o menu anterior?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);

        if (answer.equals("N") && !input[0].equals("N")) return input;

        else return null;
    }
    public static String[] MenuSmartSpeakerUpdate() {
        String input[] = new String[7];
        String answer = "N";
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Smart Speaker-----------\n\n");

        System.out.print("Código de fabrica do dispositivo que pretende alterar ou a letra [N] para voltar para atrás: ");
        input[0] = scanner.next();

        if(!input[0].equals("N")) {

            System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print(" Código de Fábrica: ");
                input[1] = scanner.next();
            }
            else {
                input[1] = "#";
            }

            System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Quantidade de energia diária gasta : ");
                input[2] = scanner.next();
            }
            else {
                input[2] = "#";
            }

            System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Custo de instalação : ");
                input[3] = scanner.next();
            }
            else {
                input[3] = "#";
            }

            System.out.print("Deseja alterar o Canal?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Canal : ");
                input[4] = scanner.next();
            }
            else {
                input[4] = "#";
            }

            System.out.print("Deseja alterar o Volume Máximo?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Volume Máximo : ");
                input[5] = scanner.next();
            }
            else {
                input[5] = "#";
            }

            System.out.print("Deseja alterar a Marca?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Marca : ");
                input[6] = scanner.next();
            }
            else {
                input[6] = "#";
            }
        }

        System.out.print("Voltar para o menu anterior?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);

        if (answer.equals("N") && !input[0].equals("N")) return input;

        else return null;
    }
    public static String[] MenuSmartCamaraUpdate() {
        String input[] = new String[6];
        String answer = "N";
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------Smart Speaker-----------\n\n");

        System.out.print("Código de fabrica do dispositivo que pretende alterar ou a letra [N] para voltar para atrás: ");
        input[0] = scanner.next();

        if(!input[0].equals("N")) {

            System.out.print("Deseja alterar o codigo de fabrica?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print(" Código de Fábrica: ");
                input[1] = scanner.next();
            }
            else {
                input[1] = "#";
            }

            System.out.print("Deseja alterar a energia diária gasta?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Quantidade de energia diária gasta : ");
                input[2] = scanner.next();
            }
            else {
                input[2] = "#";
            }

            System.out.print("Deseja alterar o custo de instalação?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Custo de instalação : ");
                input[3] = scanner.next();
            }
            else {
                input[3] = "#";
            }

            System.out.print("Deseja alterar a resolução:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Resolução (pixeis): ");
                input[4] = scanner.next();
            }
            else {
                input[4] = "#";
            }

            System.out.print("Deseja alterar o armazenamento:?[Y ou N]: ");
            answer = scanner.next().toUpperCase(Locale.ROOT);
            if (answer.equals("Y")) {
                System.out.print("Armazenamento (bytes): ");
                input[5] = scanner.next();
            }
            else {
                input[5] = "#";
            }

        }
        System.out.print("Voltar para o menu anterior?[Y ou N]: ");
        answer = scanner.next().toUpperCase(Locale.ROOT);

        if (answer.equals("N") && !input[0].equals("N") ) return input;

        else return null;

    }


}



