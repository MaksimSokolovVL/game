package com.game.util;

import com.game.domain.dto.PlayerCreateRequestDto;
import com.game.exception.PlayerCreationException;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Objects;

public class ValidationUtility {

    public static PlayerCreateRequestDto validationPlayerCreateRequestDto(PlayerCreateRequestDto playerRequestDto) {

        boolean isFieldsNotNull = Objects.nonNull(playerRequestDto.getName())
                && Objects.nonNull(playerRequestDto.getTitle())
                && Objects.nonNull(playerRequestDto.getExperience())
                && Objects.nonNull(playerRequestDto.getRace())
                && Objects.nonNull(playerRequestDto.getProfession())
                && Objects.nonNull(playerRequestDto.getBirthday());

        if (!isFieldsNotNull
                || playerRequestDto.getName().length() > 12
                || playerRequestDto.getTitle().length() > 30
                || StringUtils.isEmpty(playerRequestDto.getName())
                || !(playerRequestDto.getExperience() >= 0)
                || !(playerRequestDto.getExperience() <= 10_000_000)
                || playerRequestDto.getBirthday() < 0
                || !(playerRequestDto.getBirthday() >= Instant.parse("2000-01-01T00:00:00.00Z").toEpochMilli())
                || !(playerRequestDto.getBirthday() <= Instant.parse("3000-01-01T00:00:00.00Z").toEpochMilli())
        ) {
            throw new PlayerCreationException("Не корректно заполнены поля в PlayerCreateRequestDto");
        }
        return playerRequestDto;
    }
}
