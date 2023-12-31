package com.game.repository;

import com.game.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
}