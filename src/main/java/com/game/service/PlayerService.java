package com.game.service;

import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.mapper.MapperPlayerToPlayersResponseDto;
import com.game.repository.PlayerDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerDao playerRepo;

    private final MapperPlayerToPlayersResponseDto toPlayersResponseDto;

    public PlayerService(
            PlayerDao playerRepo,
            MapperPlayerToPlayersResponseDto mapperPlayerToPlayersResponseDto
    ) {
        this.playerRepo = playerRepo;
        this.toPlayersResponseDto = mapperPlayerToPlayersResponseDto;
    }

    @Transactional(readOnly = true)
    public List<PlayerResponseDto> getAllUsers() {
        return toPlayersResponseDto.playersToPlayersDto(playerRepo.getAllUsers());
    }
}
