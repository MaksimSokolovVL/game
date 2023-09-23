package com.game.controller;

import com.game.domain.dto.PlayerResponseDto;
import com.game.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/rest/players")
    public List<PlayerResponseDto> getPlayers() {
        return playerService.getAllUsers();
    }
}
