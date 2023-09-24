package com.game.service;

import com.game.domain.dto.PlayerCreateRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.mapper.PlayerDtoMapper;
import com.game.repository.PlayerDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.game.util.PlayerFilterUtility.dynamicComparator;
import static com.game.util.PlayerFilterUtility.isActive;
import static com.game.util.PlayerFilterUtility.isActiveBetween;
import static com.game.util.ValidationUtility.validationPlayerCreateRequestDto;

@Service
public class PlayerService {
    private final PlayerDao playerRepo;
    private final PlayerDtoMapper mapper;

    private int countPlayers;

    public PlayerService(
            PlayerDao playerRepo,
            PlayerDtoMapper playerDtoMapper
    ) {
        this.playerRepo = playerRepo;
        this.mapper = playerDtoMapper;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers(PlayerFilterRequestDto filterRequestDto) {
        List<Player> players = playerRepo.getAllPlayers();

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


        return new ResponseEntity<>(
                mapper.playersToPlayersResponseDto(players),
                HttpStatus.OK);
    }

    public ResponseEntity<Integer> getCountPlayers() {
        return new ResponseEntity<>(countPlayers, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<PlayerResponseDto> getPlayer(Long id) {

        return new ResponseEntity<>(
                mapper.playerToPlayerResponseDto(validationId(id)),
                HttpStatus.OK);
    }

    @Transactional()
    public ResponseEntity<PlayerResponseDto> createPlayerInDataBase(PlayerCreateRequestDto playerRequestDto) {
        Player player = mapper.playerRequestDtoToPlayer(validationPlayerCreateRequestDto(playerRequestDto));
        Player resultPlayer = playerRepo.savePlayer(player);

        return new ResponseEntity<>(
                mapper.playerToPlayerResponseDto(resultPlayer),
                HttpStatus.OK
        );
    }

    private Player validationId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Не верный формат id");
        }

        return playerRepo.getPlayer(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Players с таким id:%d не найден", id)));
    }
}