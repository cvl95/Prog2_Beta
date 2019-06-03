package GameEngine;

import java.util.ArrayList;
import java.util.List;

public class BotScore implements  Comparable<BotScore>{
    private final List<Integer> scores = new ArrayList<>();
    private int total;
    private double average;

    public void addScore(int value) {
        scores.add(value);
        total += value;
        average = total / scores.size();
    }

    public List<Integer> getScores() {
        return scores;
    }

    public int getTotal() {
        return total;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public int compareTo(BotScore other) {
        return total - other.getTotal();
    }
    @Override
    public String toString() {
        return String.format("Total: %d; Average: %.2f; Scores: %s", total, average, scores);
    }
}
