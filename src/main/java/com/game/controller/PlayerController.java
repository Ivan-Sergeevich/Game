package com.game.controller;

import com.game.entity.Player;
import com.game.exception.InvalidArgumentException;
import com.game.exception.NotFoundException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService service) {
        this.playerService = service;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    // TODO как то надо реализовать фильтры, добавить зависмость от pageNumber и pageSize
    @GetMapping(value = "/rest/players")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok().body(playerService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something is wrong.");
        }
    }

    @GetMapping(value = "/rest/players/count")
    public ResponseEntity getCount() {
        try {
            return ResponseEntity.ok().body(playerService.findAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something is wrong.");
        }
    }

    @DeleteMapping(value = "/rest/players/{id}")
    public ResponseEntity deletePlayerById (@PathVariable(value = "id") Integer id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().body("Player successfully deleted.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid id");
        }
    }


    // TODO в ответ надо отослать экземпляр созданного игрока
    @PostMapping(value = "/rest/players")
    public ResponseEntity createPlayer(@RequestBody Player player) {
        try {
            playerService.createPlayer(player);
            return ResponseEntity.ok().body("Player saved");
        } catch (InvalidArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something is wrong.");
        }
    }

    // TODO реализовать метод гет на одного игрока, с возвращением плаера и исключениями
    @GetMapping (value = "/rest/players/{id}")
    public ResponseEntity getPlayerById (@PathVariable(name = "id") Integer id) {
        return null;
    }

    // TODO реализовать метод апдейт со всеми вытекающими
    @PostMapping (value = "/rest/players/{id}")
    public ResponseEntity updatePlayer(@PathVariable(name = "id") Integer id) {
        return null;
    }
}
