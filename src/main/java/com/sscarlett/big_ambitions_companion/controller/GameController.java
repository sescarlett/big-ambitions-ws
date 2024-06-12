package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Game;
import com.sscarlett.big_ambitions_companion.service.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/game", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @GetMapping(value = "/list/{userId}", produces = "application/json")
    public List<Game> getUsersGames(@PathVariable Integer userId) {
        return gameService.getUsersGames(userId);
    }

    @PostMapping(value = "/new/{userId}")
    public void postNewGame(@RequestBody Game game, @PathVariable Integer userId) {
        gameService.postNewGame(userId, game);
    }

    @DeleteMapping(value = "/delete/{gameId}")
    public void deleteGame(@PathVariable Integer gameId) {
        gameService.deleteGame(gameId);
    }
}
