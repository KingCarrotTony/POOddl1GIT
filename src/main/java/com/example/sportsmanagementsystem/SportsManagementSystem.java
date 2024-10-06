package com.example.sportsmanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SportsManagementSystem {
    protected List<Player> players;
    List<Match> matches;

    public SportsManagementSystem() {
        players = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public void createPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
    }

    public void removePlayer(String name) {
        players.removeIf(player -> player.getName().equals(name));
    }

    public void showPlayers() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public void rankPlayers() {
        players.sort(Comparator.comparingDouble(Player::getScore).reversed());
        showPlayers();
    }

    public void scorePlayer(String name, double score) {
        Player player = players.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado: " + name));
        player.setScore(score);
    }

    public void matchmake(String name1, String name2) {
        Player player1 = players.stream()
                .filter(p -> p.getName().equals(name1))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado: " + name1));
        Player player2 = players.stream()
                .filter(p -> p.getName().equals(name2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado: " + name2));

        if (player1 == player2) {
            System.out.println("No se puede hacer coincidir a un jugador consigo mismo.");
            return;
        }

        if (matches.stream().anyMatch(match -> (match.getPlayer1() == player1 && match.getPlayer2() == player2) ||
                (match.getPlayer1() == player2 && match.getPlayer2() == player1))) {
            System.out.println("Estos jugadores ya están emparejados.");
            return;
        }

        Match match = new Match(player1, player2);
        matches.add(match);
        System.out.println("Partido creado: " + match);
    }

    public void clearMatchmake() {
        matches.clear();
        System.out.println("Todas las coincidencias se han limpiado.");
    }

    public void randomMatchmake() {
        if (players.size() % 2 != 0) {
            System.out.println("No se puede realizar una coincidencia aleatoria con un número impar de jugadores.");
            return;
        }

        List<Player> shuffledPlayers = new ArrayList<>(players);
        Random random = new Random();
        for (int i = shuffledPlayers.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Player temp = shuffledPlayers.get(i);
            shuffledPlayers.set(i, shuffledPlayers.get(j));
            shuffledPlayers.set(j, temp);
        }

        for (int i = 0; i < shuffledPlayers.size(); i += 2) {
            Player player1 = shuffledPlayers.get(i);
            Player player2 = shuffledPlayers.get(i + 1);

            if (matches.stream().anyMatch(match -> (match.getPlayer1() == player1 && match.getPlayer2() == player2) ||
                    (match.getPlayer1() == player2 && match.getPlayer2() == player1))) {
                continue;
            }

            Match match = new Match(player1, player2);
            matches.add(match);
        }

        System.out.println("Coincidencia aleatoria completada.");
    }
}
