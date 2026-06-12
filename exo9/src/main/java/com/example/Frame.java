package com.example;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private IGenerateur generateur;
    private List<Roll> rolls;

    public Frame(IGenerateur generateur, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.generateur = generateur;
        this.rolls = new ArrayList<>();
        this.score = 0;
    }

    public boolean makeRoll() {
        int count = rolls.size();

        if (!lastFrame) {
            if (count >= 2) return false;
            if (count == 1 && rolls.get(0).getPins() == 10) return false;
        } else {
            if (count >= 3) return false;
            if (count == 2) {
                boolean firstStrike = rolls.get(0).getPins() == 10;
                boolean spare = rolls.get(0).getPins() + rolls.get(1).getPins() == 10;
                if (!firstStrike && !spare) return false;
            }
        }

        int pins = generateur.randomPin(10);
        rolls.add(new Roll(pins));
        score += pins;
        return true;
    }
    public int getScore() {
        return score;
    }
}