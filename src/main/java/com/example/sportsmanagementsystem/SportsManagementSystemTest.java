package com.example.sportsmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SportsManagementSystemTest {

    private SportsManagementSystem system;

    @BeforeEach
    void setUp() {
        system = new SportsManagementSystem();
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
    }

    @Test
    void createPlayer() {
        system.createPlayer("John");
        assertEquals(6, system.players.size()); // 因为初始有5个玩家
    }

    @Test
    void removePlayer() {
        system.removePlayer("Luisa");
        assertEquals(4, system.players.size());
        assertFalse(system.players.stream().anyMatch(p -> p.getName().equals("Luisa")));
    }

    @Test
    void showPlayers() {

    }

    @Test
    void rankPlayers() {
        List<Player> rankedPlayers = system.players;
        rankedPlayers.sort(Comparator.comparingDouble(Player::getScore).reversed());
        assertEquals("Kurt", rankedPlayers.get(0).getName());
        assertEquals("Sofia", rankedPlayers.get(1).getName());
        assertEquals("Robert", rankedPlayers.get(2).getName());
        assertEquals("Luisa", rankedPlayers.get(3).getName());
        assertEquals("Manuel", rankedPlayers.get(4).getName());
    }

    @Test
    void scorePlayer() {
        system.scorePlayer("Luisa", 5.0);
        assertEquals(5.0, system.players.stream()
                .filter(p -> p.getName().equals("Luisa"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado")).getScore());
    }

    @Test
    void matchmake() {
        system.matchmake("Luisa", "Manuel");
        assertTrue(system.matches.stream()
                .anyMatch(match -> match.getPlayer1().getName().equals("Luisa")
                        && match.getPlayer2().getName().equals("Manuel") ||
                        match.getPlayer1().getName().equals("Manuel")
                                && match.getPlayer2().getName().equals("Luisa")));
    }

    @Test
    void clearMatchmake() {
        system.matchmake("Luisa", "Manuel");
        system.clearMatchmake();
        assertTrue(system.matches.isEmpty());
    }

    @Test
    void randomMatchmake() {
        system.randomMatchmake();
        assertFalse(system.matches.isEmpty());
        assertTrue(system.matches.size() <= system.players.size() / 2);
    }
}