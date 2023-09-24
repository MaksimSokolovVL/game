package com.game.repository.impl;

import com.game.domain.entity.Player;
import com.game.repository.PlayerDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

}
