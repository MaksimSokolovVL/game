package com.game.mapper;

import com.game.domain.builder.PlayerResponseDtoBuilder;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperPlayersToDto {

    public List<PlayerResponseDto> playersToPlayersResponseDto(List<Player> playerList) {
        return playerList.stream().map(player -> new PlayerResponseDtoBuilder()
                .setId(player.getId())
                .setName(player.getName())
                .setTitle(player.getTitle())
                .setRace(player.getRace())
                .setProfession(player.getProfession())
                .setBirthday(player.getBirthday().toEpochMilli())
                .setBanned(player.getBanned())
                .setExp(player.getExp())
                .setLevel(player.getLevel())
                .setUntilNextLevel(player.getUntilNextLevel())
                .build()
        ).collect(Collectors.toList());
    }
}
