package com.game.service;

import com.game.domain.dto.PlayerCountRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.dto.PlayerUpdateRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService {
    ResponseEntity<List<PlayerResponseDto>> getAllPlayers(PlayerFilterRequestDto filterRequestDto);

    ResponseEntity<Integer> getCountPlayers(PlayerCountRequestDto countRequestDto);

    ResponseEntity<PlayerResponseDto> getPlayer(Long id);

    ResponseEntity<PlayerResponseDto> createPlayerToDatabase(PlayerUpdateRequestDto playerRequestDto);

    ResponseEntity<PlayerResponseDto> deletedPlayer(Long id);

    ResponseEntity<PlayerResponseDto> updatePlayer(Long id, PlayerUpdateRequestDto playerRequestDto);
}
