package com.game.domain.dto;

import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

public class PlayerResponseDto {
    private final Long id;
    private final String name;
    private final String title;
    private final Race race;
    private final Profession profession;
    private final Long birthday;
    private final Boolean banned;
    private final Integer experience;
    private final Integer level;
    private final Integer untilNextLevel;

    public PlayerResponseDto(
            Long id,
            String name,
            String title,
            Race race,
            Profession profession,
            Long birthday,
            Boolean banned,
            Integer experience,
            Integer level,
            Integer untilNextLevel
    ) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }
}
