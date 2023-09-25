package com.game.domain;

import java.time.Instant;

public interface CommonPlayer {
    String getName();
    String getTitle();
    Integer getExperience();

    default Instant getInstantBirthday() {
        return null;
    }

    default Long getLongBirthday() {
        return null;
    }
}
