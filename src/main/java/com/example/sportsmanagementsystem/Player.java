package com.example.sportsmanagementsystem;

public class Player {
    private String name;
    private double score;

    public Player(String name) {
        this.name = name;
        this.score = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
