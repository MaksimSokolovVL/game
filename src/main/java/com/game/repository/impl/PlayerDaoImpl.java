package com.game.repository.impl;

import com.game.domain.entity.Player;
import com.game.repository.PlayerDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getAllUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
        query.from(Player.class);
        return entityManager.createQuery(query).getResultList();
    }

}
