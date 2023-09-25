package com.game.repository.impl;

import com.game.domain.dto.PlayerUpdateRequestDto;
import com.game.domain.entity.Player;
import com.game.exception.DeletePlayerBiIdException;
import com.game.repository.PlayerDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.game.util.ValidationUtility.validationAndUpdateFieldsPlayer;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getAllPlayers() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
//        query.from(Player.class);
//        return entityManager.createQuery(query).getResultList();
        String jpql = "SELECT p FROM Player p";
        return entityManager.createQuery(jpql, Player.class).getResultList();
    }

    @Override
    public int getCountPlayers(Integer count) {
        String jpql = "SELECT p FROM Player p";
        return entityManager.createQuery(jpql, Player.class).getResultList().size();
    }

    @Override
    public Optional<Player> getPlayer(Long id) {
        return Optional.ofNullable(entityManager.find(Player.class, id));
    }

    @Override
    public Player savePlayer(Player player) {
        entityManager.persist(player);
        entityManager.refresh(player);
        return player;
    }

    @Override
    public void delete(Long id) {
        Player currentPlayer = entityManager.find(Player.class, id);
        if (currentPlayer == null) {
            throw new DeletePlayerBiIdException(String.format("Players с таким id:%d не найден", id));
        }
        entityManager.remove(currentPlayer);
    }

    @Override
    public Player update(Long id, PlayerUpdateRequestDto playerRequestDto) {
        Player currentPlayer = entityManager.find(Player.class, id);

        if (currentPlayer == null) {
            throw new DeletePlayerBiIdException(String.format("Players с таким id:%d не найден", id));
        }

        validationAndUpdateFieldsPlayer(playerRequestDto, currentPlayer);

        entityManager.merge(currentPlayer);

        return currentPlayer;
    }


}
