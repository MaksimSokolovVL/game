package com.game.domain.dto.abstracts;

import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

public abstract class AbstractPlayerResponseDto extends AbstractPlayerDto {
    private Long birthday;
    private Boolean banned;
    private Integer experience;

    public AbstractPlayerResponseDto() {
    }

    public AbstractPlayerResponseDto(String name, String title, Race race, Profession profession, Long birthday, Boolean banned, Integer experience) {
        super(name, title, race, profession);
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
