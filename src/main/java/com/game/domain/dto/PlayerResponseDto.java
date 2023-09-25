package com.game.domain.dto;

import com.game.domain.CommonPlayer;
import com.game.domain.dto.abstracts.AbstractPlayerResponseDto;
import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

public class PlayerResponseDto extends AbstractPlayerResponseDto implements CommonPlayer {
    private final Long id;
    private final Integer level;
    private final Integer untilNextLevel;

    public PlayerResponseDto(Long id,
                             String name,
                             String title,
                             Race race,
                             Profession profession,
                             Long birthday,
                             Boolean banned,
                             Integer experience,
                             Integer level,
                             Integer untilNextLevel) {
        super(name, title, race, profession, birthday, banned, experience);
        this.id = id;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    @Override
    public Long getLongBirthday() {
        return getBirthday();
    }
}
