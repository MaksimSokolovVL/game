package com.game.util;

import com.game.domain.model.CharacterInfo;

public class CharacterLevelCalculator {
    public static CharacterInfo calculateCharacterInfo(int experience) {

        int currentLevel = calculateCurrentLevel(experience);
        int expToNextLevel = calculateExperienceToNextLevel(currentLevel, experience);

        return new CharacterInfo(currentLevel, expToNextLevel);
    }

    private static int calculateCurrentLevel(int experience) {
        return (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
    }

    private static int calculateExperienceToNextLevel(int currentLevel, int experience) {
        return 50 * (currentLevel + 1) * (currentLevel + 2) - experience;
    }
}
