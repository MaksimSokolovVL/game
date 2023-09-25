package com.game.service.impl;

import com.game.domain.dto.PlayerCountRequestDto;
import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.entity.Player;
import com.game.mapper.PlayerDtoMapper;
import com.game.repository.PlayerDao;
import com.game.service.PlayerService;
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
import static com.game.util.ValidationUtility.isDtoFieldsNotNull;
import static com.game.util.ValidationUtility.verifyPlayerFields;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDao playerRepo;
    private final PlayerDtoMapper mapper;

    public PlayerServiceImpl(
            PlayerDao playerRepo,
            PlayerDtoMapper playerDtoMapper
    ) {
        this.playerRepo = playerRepo;
        this.mapper = playerDtoMapper;
    }

    @Override
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
                .filter(player -> isActiveBetween(player, Player::getExperience, filterRequestDto.getMinExperience(), filterRequestDto.getMaxExperience()))
                .filter(player -> isActiveBetween(player, Player::getBirthday, filterRequestDto.getAfter(), filterRequestDto.getBefore()))
                .sorted((player1, player2) -> dynamicComparator(player1, player2, filterRequestDto))
                .collect(Collectors.toList());

        players = players.stream().skip((long) (filterRequestDto.getPageNumber()) * filterRequestDto.getPageSize())
                .limit(filterRequestDto.getPageSize())
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                mapper.playersToPlayersResponseDto(players),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getCountPlayers(PlayerCountRequestDto countRequestDto) {
        List<Player> players = playerRepo.getAllPlayers();

        players = players.stream()
                .filter(player -> isActive(player, Player::getName, countRequestDto.getName()))
                .filter(player -> isActive(player, Player::getTitle, countRequestDto.getTitle()))
                .filter(player -> isActive(player, Player::getRace, countRequestDto.getRace()))
                .filter(player -> isActive(player, Player::getProfession, countRequestDto.getProfession()))
                .filter(player -> isActive(player, Player::getBanned, countRequestDto.getBanned()))
                .filter(player -> isActiveBetween(player, Player::getLevel, countRequestDto.getMinLevel(), countRequestDto.getMaxLevel()))
                .filter(player -> isActiveBetween(player, Player::getExperience, countRequestDto.getMinExperience(), countRequestDto.getMaxExperience()))
                .filter(player -> isActiveBetween(player, Player::getBirthday, countRequestDto.getAfter(), countRequestDto.getBefore()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(players.size(), HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PlayerResponseDto> getPlayer(Long id) {

        return new ResponseEntity<>(
                mapper.playerToPlayerResponseDto(validationId(id)),
                HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PlayerResponseDto> createPlayerToDatabase(PlayerUpdateRequestDto playerRequestDto) {
        isDtoFieldsNotNull(playerRequestDto);
        Player player = mapper.playerRequestDtoToPlayer(verifyPlayerFields(playerRequestDto));
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

    @Override
    @Transactional
    public ResponseEntity<PlayerResponseDto> deletedPlayer(Long id) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        playerRepo.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PlayerResponseDto> updatePlayer(Long id, PlayerUpdateRequestDto playerRequestDto) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        return new ResponseEntity<>(
                mapper.playerToPlayerResponseDto(playerRepo.update(id, playerRequestDto)),
                HttpStatus.OK);
    }
}