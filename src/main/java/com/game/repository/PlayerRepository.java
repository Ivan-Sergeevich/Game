package com.game.repository;

import com.game.entity.Player;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Integer> {
    List<Player> findAll();
}
