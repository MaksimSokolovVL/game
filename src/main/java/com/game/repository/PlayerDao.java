package com.game.repository;

import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.entity.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    List<Player> getAllPlayers();
    int getCountPlayers(Integer count);

    Optional<Player> getPlayer(Long id);

    Player savePlayer(Player player);

//    Optional<Player> delete(Long id);
    void delete(Long id);

    Player update(Long id, PlayerUpdateRequestDto playerRequestDto);
}
