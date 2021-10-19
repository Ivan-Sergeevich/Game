package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.exception.InvalidArgumentException;
import com.game.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {
    Player findById(Integer id) throws NotFoundException, InvalidArgumentException;
    Player createPlayer(Player player) throws InvalidArgumentException;
    Player updatePlayer(Integer id, Player player) throws NotFoundException, InvalidArgumentException;
    void deletePlayer(Integer id) throws NotFoundException, InvalidArgumentException;
    List<Player> findAll();
    Page<Player> findAll(Pageable var1);
}
