package com.game.service;

import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.mapper.MapperPlayersToDto;
import com.game.repository.PlayerDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.game.util.PlayerFilterUtility.*;

@Service
public class PlayerService {
    private final PlayerDao playerRepo;
    private final MapperPlayersToDto mapper;

    private int countPlayers;

    public PlayerService(
            PlayerDao playerRepo,
            MapperPlayersToDto mapperPlayersToDto
    ) {
        this.playerRepo = playerRepo;
        this.mapper = mapperPlayersToDto;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<PlayerResponseDto>> getAllUsers(PlayerFilterRequestDto filterRequestDto) {
        List<Player> players = playerRepo.getAllUsers();

        players = players.stream()
                .filter(player -> isActive(player, Player::getName, filterRequestDto.getName()))
                .filter(player -> isActive(player, Player::getTitle, filterRequestDto.getTitle()))
                .filter(player -> isActive(player, Player::getRace, filterRequestDto.getRace()))
                .filter(player -> isActive(player, Player::getProfession, filterRequestDto.getProfession()))
                .filter(player -> isActive(player, Player::getBanned, filterRequestDto.getBanned()))
                .filter(player -> isActiveBetween(player, Player::getLevel, filterRequestDto.getMinLevel(), filterRequestDto.getMaxLevel()))
                .filter(player -> isActiveBetween(player, Player::getExp, filterRequestDto.getMinExperience(), filterRequestDto.getMaxExperience()))
                .filter(player -> isActiveBetween(player, Player::getBirthday, filterRequestDto.getAfter(), filterRequestDto.getBefore()))
                .sorted((player1, player2) -> dynamicComparator(player1, player2, filterRequestDto))
                .collect(Collectors.toList());

        countPlayers = players.size();

        players = players.stream().skip((long) (filterRequestDto.getPageNumber()) * filterRequestDto.getPageSize())
                .limit(filterRequestDto.getPageSize())
                .collect(Collectors.toList());


        return new ResponseEntity<>(mapper
                .playersToPlayersResponseDto(players),
                HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Integer> getCountPlayers() {
        return new ResponseEntity<>(countPlayers, HttpStatus.OK);
    }

}
