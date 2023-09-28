package com.game.controller;

import com.game.domain.dto.PlayerCountRequestDto;
import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getPlayers(@ModelAttribute PlayerFilterRequestDto filterRequestDto) {
        return playerService.getAllPlayers(filterRequestDto);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPlayersCount(@ModelAttribute PlayerCountRequestDto countRequestDto) {
        return playerService.getCountPlayers(countRequestDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody PlayerUpdateRequestDto playerRequestDto) {
        return playerService.createPlayerToDatabase(playerRequestDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable Long id,
                                                          @RequestBody PlayerUpdateRequestDto playerRequestDto) {
        return playerService.updatePlayer(id, playerRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        return playerService.deletedPlayer(id);
    }
}
