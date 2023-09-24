package com.game.repository;

import com.game.domain.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    List<Player> getAllPlayers();
    int getCountPlayers(Integer count);

    Optional<Player> getPlayer(Long id);

    Player savePlayer(Player player);
}
