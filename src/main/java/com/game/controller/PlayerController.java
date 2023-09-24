package com.game.controller;

import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/rest/players")
    public ResponseEntity<List<PlayerResponseDto>> getPlayers(@ModelAttribute PlayerFilterRequestDto filterRequestDto) {
        return playerService.getAllUsers(filterRequestDto);
    }

    @GetMapping("/rest/players/count")
    public ResponseEntity<Integer> getPlayersCount(){
        return playerService.getCountPlayers();
    }
}
