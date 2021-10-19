package com.game.controller;

import com.game.entity.Player;
import com.game.exception.InvalidArgumentException;
import com.game.exception.NotFoundException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService service) {
        this.playerService = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // TODO как то надо реализовать фильтры
    @GetMapping("/rest/players")
    public ResponseEntity<?> findAll(@RequestParam (value = "order", required = false, defaultValue = "id") String order,
                                     @RequestParam(value = "pageNumber", required = false, defaultValue = "0") String pageNumber,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "3") String pageSize) {
        try {
            Page<Player> page = playerService.
                    findAll(PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize),
                            Sort.by(order.toLowerCase(Locale.ROOT))));
            return ResponseEntity.ok().body(page.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something is wrong.");
        }
    }

    // Готов, тесты прошёл.
    @GetMapping(value = "(/rest/players/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        try {
            Player foundedPlayer = playerService.findById(id);
            return ResponseEntity.ok(foundedPlayer);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }

    // Готов, тесты прошёл.
    @GetMapping(value = "/rest/players/count")
    public ResponseEntity<?> getCount() {
        try {
            return ResponseEntity.ok().body(playerService.findAll().size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something is wrong.");
        }
    }

    // Готов, тесты прошёл.
    @DeleteMapping(value = "/rest/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Integer id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().body("Player successfully deleted.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }

    // Готов, тесты прошёл.
    @PostMapping("/rest/players")
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        try {
            Player createdPlayer = playerService.createPlayer(player);
            return ResponseEntity.ok().body(createdPlayer);
        } catch (InvalidArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    // TODO реализовать метод апдейт со всеми вытекающими
    @PostMapping(value = "/rest/players/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable(name = "id") Integer id) {
        return null;
    }
}
