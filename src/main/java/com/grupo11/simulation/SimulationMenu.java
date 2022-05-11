package com.grupo11.simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SimulationMenu {
    public LocalDateTime[] manualSimulationMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira a data e hora de início no formato (dd/mm/aaaa hora:minutos): ");
        String startInput = scanner.nextLine();
        System.out.print("Insira a data e hora do fim no formato (dd/mm/aaaa hh:mm): ");
        String endInput = scanner.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime start = LocalDateTime.parse(startInput, dateTimeFormatter);
            LocalDateTime end = LocalDateTime.parse(endInput, dateTimeFormatter);
            if (end.isBefore(start)) {
                System.out.println("A data de início precisa ser anterior a data do fim!!");
                return null;
            }
            return new LocalDateTime[]{start, end};
        }
        catch(DateTimeParseException e) {
            System.out.println("Formato inválido! Tente novamente!");
            System.out.println("Exemplo válido: 05/08/1990 08:45");
            return null;
        }
    }

    public String autoSimulationMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o caminho do arquivo de simulação automática: ");
        return scanner.next();
    }
}
