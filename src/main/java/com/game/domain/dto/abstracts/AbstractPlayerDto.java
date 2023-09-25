package com.game.domain.dto.abstracts;

import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

public abstract class AbstractPlayerDto {
    private String name;
    private String title;
    private Race race;
    private Profession profession;

    public AbstractPlayerDto(String name, String title, Race race, Profession profession) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
    }

    public AbstractPlayerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
