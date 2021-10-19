package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.exception.InvalidArgumentException;
import com.game.exception.NotFoundException;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    PlayerRepository repository;

    @Autowired
    public void setRepository(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Player findById(Integer id) throws NotFoundException, InvalidArgumentException {
        checkId(id);
        return repository.findById(id).get();
    }

    @Override
    public Player createPlayer(Player player) throws InvalidArgumentException {
        checkParameters(player);

        Player createdPlayer = new Player(player.getName(),
                player.getTitle(),
                player.getRace(),
                player.getProfession(),
                player.getBirthday(),
                player.isBanned(),
                player.getExperience(),
                player.getLevel(),
                player.getUntilNextLevel());

        repository.save(createdPlayer);
        return createdPlayer;
    }

    @Override
    public Player updatePlayer(Integer id, Player player) throws NotFoundException, InvalidArgumentException {
        checkId(id);

        Player updated = repository.findById(id).get();
        updated.setName(player.getName());
        updated.setTitle(player.getTitle());
        updated.setRace(player.getRace());
        updated.setProfession(player.getProfession());
        updated.setBirthday(player.getBirthday());
        updated.setBanned(player.isBanned());
        updated.setExperience(player.getExperience());
        updated.setLevel(player.getLevel());
        updated.setUntilNextLevel(player.getUntilNextLevel());

        repository.save(updated);
        return null;
    }

    @Override
    public void deletePlayer(Integer id) throws NotFoundException, InvalidArgumentException {
        checkId(id);
        repository.deleteById(id);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Player> findAll(Pageable var1) {
        return repository.findAll(var1);
    }

    private void checkId(Integer id) throws NotFoundException, InvalidArgumentException {
        if (id <= 0)
            throw new InvalidArgumentException("Please specify valid Id");

        if (!repository.findById(id).isPresent())
            throw new NotFoundException("Player with specified ID was not found");
    }

    private void checkParameters (Player player) throws InvalidArgumentException {
        if (player.getName() == null ||
                player.getTitle().isEmpty() ||
                player.getRace() == null ||
                player.getProfession() == null ||
                player.getBirthday() == null ||
                player.getExperience() == null)
            throw new InvalidArgumentException("Not all parameters are specified");

        if (player.getName().length() > 12 || player.getTitle().length() > 30)
            throw new InvalidArgumentException("The name or title is too long.");

        if (player.getName().trim().isEmpty())
            throw new InvalidArgumentException("Empty name.");

        if (player.getExperience() < 0 || player.getExperience() > 10000000)
            throw new InvalidArgumentException("Invalid experience.");

        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2000, Calendar.JANUARY , 1);
        if (player.getBirthday().getTime() < 0 ||
                player.getBirthday().before(cal.getTime()))
            throw new InvalidArgumentException("Invalid birthday");
        cal.set(3000, Calendar.DECEMBER, 31);
        if (player.getBirthday().after(cal.getTime()))
            throw new InvalidArgumentException("Invalid birthday");
    }
}
