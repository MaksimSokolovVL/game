package com.game.repository;

import com.game.domain.entity.Player;

import java.util.List;

public interface PlayerDao {
    List<Player> getAllUsers();
    List<Player> getCountUsers(Integer count);
}
