package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Game;

import java.util.List;

public interface GameService {

    /**
     * gets all the games a user has started
     * @param userId user id
     * @return list of games
     */
    List<Game> getUsersGames(Integer userId);

    /**
     * creates a new game
     *
     * @param userId user id
     * @param game   info
     */
    void postNewGame(Integer userId, Game game);

    /**
     * delete a game
     * @param gameId id
     */
    void deleteGame(Integer gameId);
}
