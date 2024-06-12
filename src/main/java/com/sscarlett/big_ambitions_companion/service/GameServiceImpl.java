package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.BusinessDao;
import com.sscarlett.big_ambitions_companion.dao.GameDao;
import com.sscarlett.big_ambitions_companion.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private BusinessDao businessDao;

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
        log.info("newId: " + newId);
        game.setGameId(newId);
        log.info("business: " + game);
        gameDao.insertNewGame(game);
        gameDao.insertGameX(userId, newId);
    }

    /**
     * delete a game
     *
     * @param gameId id
     */
    @Override
    public void deleteGame(Integer gameId) {
        List<Integer> businessIdList = gameDao.selectBusinessIds(gameId);

        for(Integer id: businessIdList) {
            businessDao.deleteGameX(id);
            businessDao.deleteProductXAll(id);
            businessDao.deleteBusiness(id);
        }

        gameDao.deleteUserX(gameId);
        gameDao.deleteGame(gameId);
    }
}
