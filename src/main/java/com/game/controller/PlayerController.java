package com.game.controller;

import com.game.domain.dto.PlayerCountRequestDto;
import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<Integer> getPlayersCount(@ModelAttribute PlayerCountRequestDto countRequestDto) {
        return playerService.getCountPlayers(countRequestDto);
    }


    @GetMapping("/rest/players/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @PostMapping("/rest/players")
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody PlayerUpdateRequestDto playerRequestDto) {
        return playerService.createPlayerToDatabase(playerRequestDto);
    }

    @PostMapping("/rest/players/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable Long id,
                                                          @RequestBody PlayerUpdateRequestDto playerRequestDto) {
        return playerService.updatePlayer(id, playerRequestDto);
    }


    @DeleteMapping("/rest/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        return playerService.deletedPlayer(id);
    }
}
