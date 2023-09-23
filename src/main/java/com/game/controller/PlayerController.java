package com.game.controller;

import com.game.domain.builder.PlayerBuilder;
import com.game.domain.entity.Player;
import com.game.domain.enums.Profession;
import com.game.domain.enums.Race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

//    private final PlayersRepository playersRepo;
//
//
//    public PlayerController(PlayersRepository playersRepo) {
//        this.playersRepo = playersRepo;
//    }
    /*

3	Эззэссэль	шипящая	DWARF	CLERIC	2006-02-28	1	804	3	196
4	Бэлан	Тсе Раа	DWARF	ROGUE	2008-02-25	1	44553	29	1947*/

    List<Player> players = new ArrayList<>();
    Player player1 = new PlayerBuilder()
            .setName("Ниус")
            .setTitle("Приходящий Без Шума")
            .setRace(Race.HOBBIT)
            .setProfession(Profession.ROGUE)
            .setBirthday(Instant.parse("2010-10-12T00:00:00Z"))
            .setBanned(false)
            .setExp(58347)
            .setLevel(33)
            .setUntilNextLevel(1153)
            .build();

    Player player2 = new PlayerBuilder()
            .setName("Никрашш")
            .setTitle("Найт Вульф")
            .setRace(Race.ORC)
            .setProfession(Profession.WARLOCK)
            .setBirthday(Instant.parse("2010-02-14T00:00:00Z"))
            .setBanned(false)
            .setExp(174403)
            .setLevel(58)
            .setUntilNextLevel(2597)
            .build();


    @GetMapping("/rest/players")
    public List<Player> getPlayers() {
        players.add(player1);
        players.add(player2);
        return players;
    }
}
