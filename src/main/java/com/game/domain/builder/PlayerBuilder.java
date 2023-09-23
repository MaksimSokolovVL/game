package com.game.domain.builder;

import com.game.domain.entity.Player;
import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

import java.time.Instant;

public class PlayerBuilder {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Instant birthday;
    private boolean banned;
    private int exp;
    private int level;
    private int untilNextLevel;

    public PlayerBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PlayerBuilder setRace(Race race) {
        this.race = race;
        return this;
    }

    public PlayerBuilder setProfession(Profession profession) {
        this.profession = profession;
        return this;
    }

    public PlayerBuilder setBirthday(Instant birthday) {
        this.birthday = birthday;
        return this;
    }

    public PlayerBuilder setBanned(Boolean banned) {
        this.banned = banned;
        return this;
    }

    public PlayerBuilder setExp(int exp) {
        this.exp = exp;
        return this;
    }

    public PlayerBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public PlayerBuilder setUntilNextLevel(int untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
        return this;
    }

    public Player build() {
        return new Player(id, name, title, race, profession, birthday, banned, exp, level, untilNextLevel);
    }
}
