package com.example.sportsmanagementsystem;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SportsManagementSystem system = new SportsManagementSystem();

        // Añade jugadores iniciales
        system.createPlayer("Luisa");
        system.scorePlayer("Luisa", 4.5);
        system.createPlayer("Manuel");
        system.scorePlayer("Manuel", 2.7);
        system.createPlayer("Kurt");
        system.scorePlayer("Kurt", 4.0);
        system.createPlayer("Sofia");
        system.scorePlayer("Sofia", 3.8);
        system.createPlayer("Robert");
        system.scorePlayer("Robert", 3.8);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts[0].equals("create")) {
                system.createPlayer(parts[1]);
            } else if (parts[0].equals("remove")) {
                system.removePlayer(parts[1]);
            } else if (parts[0].equals("show")) {
                system.showPlayers();
            } else if (parts[0].equals("rank")) {
                system.rankPlayers();
            } else if (parts[0].equals("score")) {
                system.scorePlayer(parts[1], Double.parseDouble(parts[2]));
            } else if (parts[0].equals("matchmake")) {
                system.matchmake(parts[1], parts[2]);
            } else if (parts[0].equals("clear_matchmake")) {
                system.clearMatchmake();
            } else if (parts[0].equals("random_matchmake")) {
                system.randomMatchmake();
            } else if (parts[0].equals("exit")) {
                break;
            } else {
                System.out.println("Comando inválido.");
            }
        }
    }

}
