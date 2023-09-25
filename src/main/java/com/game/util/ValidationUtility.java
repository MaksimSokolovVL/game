package com.game.util;

import com.game.domain.CommonPlayer;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.entity.Player;
import com.game.domain.model.CharacterInfo;
import com.game.exception.PlayerCreationException;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Consumer;

public class ValidationUtility {

    public static <T extends CommonPlayer> T verifyPlayerFields(T playerRequestDto) {

        if (playerRequestDto.getName().length() > 12
                || playerRequestDto.getTitle().length() > 30
                || StringUtils.isEmpty(playerRequestDto.getName())
                || !(playerRequestDto.getExperience() >= 0)
                || !(playerRequestDto.getExperience() <= 10_000_000)
        ) {
            throw new PlayerCreationException(String.format("Не корректно заполнены поля ЗНАЧЕНИЙ в %s", playerRequestDto.getClass()));
        }

        if ((playerRequestDto instanceof PlayerUpdateRequestDto || playerRequestDto instanceof PlayerResponseDto)
                && (playerRequestDto.getLongBirthday() < 0
                || playerRequestDto.getLongBirthday() <= Instant.parse("2000-01-01T00:00:00.00Z").toEpochMilli()
                || playerRequestDto.getLongBirthday() >= Instant.parse("3000-01-01T00:00:00.00Z").toEpochMilli())
        ) {
            throw new PlayerCreationException(String.format("Не корректно заполнены поля ДНЯ РОЖДЕНИЯ ДТО в %s", playerRequestDto.getClass()));
        }

        if (playerRequestDto instanceof Player
                && (playerRequestDto.getInstantBirthday().toEpochMilli() < 0
                || playerRequestDto.getInstantBirthday().toEpochMilli() <= Instant.parse("2000-01-01T00:00:00.00Z").toEpochMilli()
                || playerRequestDto.getInstantBirthday().toEpochMilli() >= Instant.parse("3000-01-01T00:00:00.00Z").toEpochMilli())
        ) {
            throw new PlayerCreationException(String.format("Не корректно заполнены поля ДНЯ РОЖДЕНИЯ в %s", playerRequestDto.getClass()));
        }
        return playerRequestDto;
    }

    public static void isDtoFieldsNotNull(PlayerUpdateRequestDto playerRequestDto) {
        if (!(Objects.nonNull(playerRequestDto.getName())
                && Objects.nonNull(playerRequestDto.getTitle())
                && Objects.nonNull(playerRequestDto.getExperience())
                && Objects.nonNull(playerRequestDto.getRace())
                && Objects.nonNull(playerRequestDto.getProfession())
                && Objects.nonNull(playerRequestDto.getBirthday()))
        ) {
            throw new PlayerCreationException("Не корректно заполнены поля в PlayerCreateRequestDto есть поля с null");
        }
    }

    public static Player validationAndUpdateFieldsPlayer(PlayerUpdateRequestDto playerRequestDto, Player currentPlayer) {
        updateFieldIfNotNull(playerRequestDto.getName(), currentPlayer::setName);
        updateFieldIfNotNull(playerRequestDto.getTitle(), currentPlayer::setTitle);
        updateFieldIfNotNull(playerRequestDto.getRace(), currentPlayer::setRace);
        updateFieldIfNotNull(playerRequestDto.getProfession(), currentPlayer::setProfession);
        updateFieldIfNotNull(playerRequestDto.getProfession(), currentPlayer::setProfession);
        updateFieldIfNotNull(playerRequestDto.getBanned(), currentPlayer::setBanned);
        updateFieldIfNotNull(playerRequestDto.getExperience(), currentPlayer::setExperience);

        if (playerRequestDto.getBirthday() != null) {
            currentPlayer.setBirthday(Instant.ofEpochMilli(playerRequestDto.getBirthday()));
        }

        CharacterInfo characterInfo = CharacterLevelCalculator.calculateCharacterInfo(currentPlayer.getExperience());

        currentPlayer.setLevel(characterInfo.getCurrentLevel());
        currentPlayer.setUntilNextLevel(characterInfo.getUntilNextLevel());

        verifyPlayerFields(currentPlayer);

        return currentPlayer;
    }

    private static <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
