package com.game.util;

import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.entity.Player;
import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

import java.time.Instant;
import java.util.function.Function;

public class PlayerFilterUtility {
    public static <T> boolean isActive(Player player, Function<Player, T> valueExtractor, T filterValue) {
        if (filterValue == null) {
            return true;
        }
        if (filterValue instanceof String) {
            String stringValue = (String) filterValue;
            String fieldValue = String.valueOf(valueExtractor.apply(player));
            return stringValue.isEmpty() || fieldValue.contains(stringValue);
        }
        if (filterValue instanceof Race || filterValue instanceof Profession) {
            return filterValue.equals(valueExtractor.apply(player));
        }
        if (filterValue instanceof Boolean) {
            return filterValue.equals(valueExtractor.apply(player));
        }
        return false;
    }

    public static <T> boolean isActiveBetween(Player player, Function<Player, T> valueExtractor, T filterMinValue, T filterMaxValue) {
        if (filterMinValue == null || filterMaxValue == null) {
            return true;
        }

        if (filterMinValue instanceof Integer && filterMaxValue instanceof Integer) {
            int integerMinValue = (int) filterMinValue;
            int integerMaxValue = (int) filterMaxValue;
            int playerValue = (int) valueExtractor.apply(player);
            return playerValue >= integerMinValue && playerValue <= integerMaxValue;
        }

        if (filterMinValue instanceof Long && filterMaxValue instanceof Long) {
            long minValue = (long) filterMinValue;
            long maxValue = (long) filterMaxValue;
            Instant playerValue = (Instant) valueExtractor.apply(player);
            long playerTimestamp = playerValue.toEpochMilli();
            return playerTimestamp >= minValue && playerTimestamp <= maxValue;
        }
        return false;
    }

    public static Integer dynamicComparator(Player player1, Player player2, PlayerFilterRequestDto filter) {
        int result;

        switch (filter.getOrder()) {
            case NAME:
                result = player1.getName().compareTo(player2.getName());
                break;
            case EXPERIENCE:
                result = Integer.compare(player1.getExp(), player2.getExp());
                break;
            case BIRTHDAY:
                result = player1.getBirthday().compareTo(player2.getBirthday());
                break;
            default:
                result = Long.compare(player1.getId(), player2.getId());
        }
        return result;
    }
}