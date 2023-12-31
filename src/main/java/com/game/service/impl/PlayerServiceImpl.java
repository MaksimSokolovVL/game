package com.game.service.impl;

import com.game.domain.dto.PlayerCountRequestDto;
import com.game.domain.dto.PlayerFilterRequestDto;
import com.game.domain.dto.PlayerResponseDto;
import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.entity.Player;
import com.game.mapper.PlayerDtoMapper;
import com.game.repository.PlayerRepo;
import com.game.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.game.util.PlayerFilterUtility.dynamicComparator;
import static com.game.util.PlayerFilterUtility.filterPlayersByFields;
import static com.game.util.ValidationUtility.isDtoFieldsNotNull;
import static com.game.util.ValidationUtility.validationAndUpdateFieldsPlayer;
import static com.game.util.ValidationUtility.verifyPlayerFields;

@Service
public class PlayerServiceImpl implements PlayerService {
    //    private final PlayerDao playerRepo;
    private final PlayerRepo playerRepo;
    private final PlayerDtoMapper mapper;

    public PlayerServiceImpl(
//            PlayerDao playerRepo,
            PlayerRepo playerRepo,
            PlayerDtoMapper playerDtoMapper
    ) {
        this.playerRepo = playerRepo;
        this.mapper = playerDtoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers(PlayerFilterRequestDto filterRequestDto) {
//        List<Player> players = playerRepo.getAllPlayers();
        List<Player> players = playerRepo.findAll();

        players = filterPlayersByFields(filterRequestDto, players).stream()
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
//        List<Player> players = playerRepo.getAllPlayers();
        List<Player> players = playerRepo.findAll();

        players = filterPlayersByFields(countRequestDto, players);
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


//        Player resultPlayer = playerRepo.savePlayer(player);
        Player resultPlayer = playerRepo.save(player);

        return new ResponseEntity<>(
                mapper.playerToPlayerResponseDto(resultPlayer),
                HttpStatus.OK
        );
    }

    private Player validationId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Не верный формат id");
        }

//        return playerRepo.getPlayer(id)
        return playerRepo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Players с таким id:%d не найден", id)));
    }

    @Override
    @Transactional
    public ResponseEntity<PlayerResponseDto> deletedPlayer(Long id) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        playerRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Players с таким id:%d не найден", id)));

        playerRepo.deleteById(id);
//        playerRepo.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PlayerResponseDto> updatePlayer(Long id, PlayerUpdateRequestDto playerRequestDto) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        Player currentPlayer = playerRepo.findById(id)
//        Player currentPlayer = playerRepo.getPlayer(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Players с таким id:%d не найден", id)));

        validationAndUpdateFieldsPlayer(playerRequestDto, currentPlayer);

        return new ResponseEntity<>(
//                mapper.playerToPlayerResponseDto(playerRepo.update(id, playerRequestDto)),
                mapper.playerToPlayerResponseDto(playerRepo.save(currentPlayer)),
                HttpStatus.OK);
    }
}