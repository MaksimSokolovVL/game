package com.game.controller;

import com.game.domain.dto.PlayerCreateRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return playerService.getAllPlayers(filterRequestDto);
    }

    @GetMapping("/rest/players/count")
    public ResponseEntity<Integer> getPlayersCount() {
        return playerService.getCountPlayers();
    }


    @GetMapping("/rest/players/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @PostMapping("/rest/players")
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody PlayerCreateRequestDto playerRequestDto){
        return playerService.createPlayerInDataBase(playerRequestDto);
    }
}
