package com.game.mapper;

import com.game.domain.builder.PlayerBuilder;
import com.game.domain.builder.PlayerResponseDtoBuilder;
import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.domain.model.CharacterInfo;
import com.game.util.CharacterLevelCalculator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerDtoMapper {

    public List<PlayerResponseDto> playersToPlayersResponseDto(List<Player> playerList) {
        return playerList.stream().map(player -> new PlayerResponseDtoBuilder()
                .setId(player.getId())
                .setName(player.getName())
                .setTitle(player.getTitle())
                .setRace(player.getRace())
                .setProfession(player.getProfession())
                .setBirthday(player.getBirthday().toEpochMilli())
                .setBanned(player.getBanned())
                .setExp(player.getExperience())
                .setLevel(player.getLevel())
                .setUntilNextLevel(player.getUntilNextLevel())
                .build()
        ).collect(Collectors.toList());
    }

    public PlayerResponseDto playerToPlayerResponseDto(Player player) {
        return new PlayerResponseDtoBuilder()
                .setId(player.getId())
                .setName(player.getName())
                .setTitle(player.getTitle())
                .setRace(player.getRace())
                .setProfession(player.getProfession())
                .setBirthday(player.getBirthday().toEpochMilli())
                .setBanned(player.getBanned())
                .setExp(player.getExperience())
                .setLevel(player.getLevel())
                .setUntilNextLevel(player.getUntilNextLevel())
                .build();
    }


    public Player playerRequestDtoToPlayer(PlayerUpdateRequestDto playerRequestDto) {
        CharacterInfo characterInfo = CharacterLevelCalculator.calculateCharacterInfo(playerRequestDto.getExperience());
        return new PlayerBuilder()
                .setName(playerRequestDto.getName())
                .setTitle(playerRequestDto.getTitle())
                .setRace(playerRequestDto.getRace())
                .setProfession(playerRequestDto.getProfession())
                .setBirthday(Instant.ofEpochMilli(playerRequestDto.getBirthday()))
                .setBanned(playerRequestDto.getBanned())
                .setExp(playerRequestDto.getExperience())
                .setLevel(characterInfo.getCurrentLevel())
                .setUntilNextLevel(characterInfo.getUntilNextLevel())
                .build();
    }
}
