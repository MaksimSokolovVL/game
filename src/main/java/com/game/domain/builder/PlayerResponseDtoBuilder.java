package com.game.domain.builder;

import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

import java.time.Instant;

public class PlayerResponseDtoBuilder {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer exp;
    private Integer level;
    private Integer untilNextLevel;

    public PlayerResponseDto build() {
        return new PlayerResponseDto(id, name, title, race, profession, birthday, banned, exp, level, untilNextLevel);
    }

    public PlayerResponseDtoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerResponseDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerResponseDtoBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PlayerResponseDtoBuilder setRace(Race race) {
        this.race = race;
        return this;
    }

    public PlayerResponseDtoBuilder setProfession(Profession profession) {
        this.profession = profession;
        return this;
    }

    public PlayerResponseDtoBuilder setBirthday(Long birthday) {
        this.birthday = birthday;
        return this;
    }

    public PlayerResponseDtoBuilder setBanned(Boolean banned) {
        this.banned = banned;
        return this;
    }

    public PlayerResponseDtoBuilder setExp(Integer exp) {
        this.exp = exp;
        return this;
    }

    public PlayerResponseDtoBuilder setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public PlayerResponseDtoBuilder setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
        return this;
    }
}
