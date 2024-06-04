package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.GameDao;
import com.sscarlett.big_ambitions_companion.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    /**
     * gets all the games a user has started
     *
     * @param userId user id
     * @return list of games
     */
    @Override
    public List<Game> getUsersGames(Integer userId) {
        return gameDao.selectUsersGames(userId);
    }

    /**
     * creates a new game
     *
     * @param userId user id
     * @param game   info
     */
    @Override
    public void postNewGame(Integer userId, Game game) {
        Integer newId = gameDao.selectMaxId();
        game.setGameId(newId);
        gameDao.insertNewGame(game);
        gameDao.insertGameX(userId, newId);
    }
}
