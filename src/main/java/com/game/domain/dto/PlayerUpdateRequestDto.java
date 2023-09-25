package com.game.domain.dto;

import com.game.domain.CommonPlayer;
import com.game.domain.dto.abstracts.AbstractPlayerResponseDto;

public class PlayerUpdateRequestDto extends AbstractPlayerResponseDto implements CommonPlayer {

    @Override
    public Long getLongBirthday() {
        return getBirthday();
    }
}
