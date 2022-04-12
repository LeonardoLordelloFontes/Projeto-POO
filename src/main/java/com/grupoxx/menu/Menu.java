package com.grupoxx.menu;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public abstract class Menu {
    public static int MenuInicial() {
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
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuFasedeConfiguracao() {
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
        System.out.println(sb.toString());
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
        System.out.println(sb.toString());
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
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuCasa() {
        StringBuilder sb = new StringBuilder("-----------Casa-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.println(sb.toString());
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
        System.out.println(sb.toString());
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
    public static String MenuAddOwner() {
        StringBuilder sb = new StringBuilder("   Propriatário   \n\n");
        sb.append("Nome: ");
        sb.append("NIF: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int MenuDispositivos() {
        StringBuilder sb = new StringBuilder("-----------Dispositivos-----------\n\n");
        sb.append("1. Adicionar \n");
        sb.append("2. Remover \n");
        sb.append("3. Atualizar \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String AdicionarFornecedordeEnergia() {
        StringBuilder sb = new StringBuilder("-----------Adicionar um Fornecedor de Energia-----------\n\n");
        sb.append("Nome \n");
        sb.append("Fórmula: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String AtualizarFornecedordeEnergia() {
        StringBuilder sb = new StringBuilder("-----------Atualizar um Fornecedor de Energia-----------\n\n");
        sb.append("Nome: \n");
        sb.append("Fórmula: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int MenuTipoDispositivo() {
        StringBuilder sb = new StringBuilder("-----------Tipo de Dispositivos-----------\n\n");
        sb.append("1. Smart Bulb \n");
        sb.append("2. Smart Speaker\n");
        sb.append("3. Smart Camara \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção (Selecionar Número): ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String MenuSmartBulbAdd() {
        StringBuilder sb = new StringBuilder("-----------Smart Bulb-----------\n\n");
        sb.append("Código de Fábrica: \n");
        sb.append("Dimensão(cm): \n");
        sb.append("Custo de Instalação: \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String MenuSmartSpeakerAdd() {
        StringBuilder sb = new StringBuilder("-----------Smart Speaker-----------\n\n");
        sb.append("Código de Fábrica: \n");
        sb.append("Canal: \n");
        sb.append("Volume Máximo: \n");
        sb.append("Marca \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String MenuSmartCamaraAdd() {
        StringBuilder sb = new StringBuilder("-----------Smart Camara-----------\n\n");
        sb.append("Código de Fábrica: \n");
        sb.append("Resolução (pixeis): \n");
        sb.append("Armazenamento (bytes) \n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String MenuSmartBulbUpdate() {
        StringBuilder sb = new StringBuilder("-----------Smart Bulb-----------\n\n");
        sb.append("1. Código de Fábrica: \n");
        sb.append("2. Dimensão(cm): \n");
        sb.append("3. Custo de instalação: \n\n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static int MenuSmartSpeakerUpdate() {
        StringBuilder sb = new StringBuilder("-----------Smart Speaker-----------\n\n");
        sb.append("1. Código de Fábrica: \n");
        sb.append("2. Canal: \n");
        sb.append("3. Volume Máximo: \n");
        sb.append("4. Marca \n");
        sb.append("5. Voltar \n\n");
        sb.append("Sua Opção: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuSmartCamaraUpdate() {
        StringBuilder sb = new StringBuilder("-----------Smart Camara-----------\n\n");
        sb.append("1. Código de Fábrica: \n");
        sb.append("2. Resolução (pixeis): \n");
        sb.append("3. Armazenamento (bytes) \n");
        sb.append("4. Voltar \n\n");
        sb.append("Sua Opção: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuRemoçao() {
        // Eu não sei como fazer estes em que temos de escolher um numero que vai sendo alterado
    return 0;
    }
}



