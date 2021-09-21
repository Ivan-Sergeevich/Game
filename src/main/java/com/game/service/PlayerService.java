package com.game.service;

import com.game.entity.Player;
import com.game.exception.InvalidArgumentException;
import com.game.exception.NotFoundException;

import java.util.List;

public interface PlayerService {
    Player getPlayerById(Integer id) throws NotFoundException;
    void createPlayer(Player player) throws InvalidArgumentException;
    void updatePlayer(Integer id, Player player) throws NotFoundException;
    void deletePlayer(Integer id) throws NotFoundException;
    List<Player> findAll();
}
