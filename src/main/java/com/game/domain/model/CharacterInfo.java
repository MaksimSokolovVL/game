package com.game.domain.model;

public class CharacterInfo {
    private final int currentLevel;
    private final int expToNextLevel;

    public CharacterInfo(int currentLevel, int expToNextLevel) {
        this.currentLevel = currentLevel;
        this.expToNextLevel = expToNextLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getUntilNextLevel() {
        return expToNextLevel;
    }
}
