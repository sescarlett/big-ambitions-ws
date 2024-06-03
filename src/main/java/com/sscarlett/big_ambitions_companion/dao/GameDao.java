package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameDao {

    @Select("SELECT COALESCE(MAX(game_id) + 1, 1) FROM game")
    Integer selectMaxId();

    @Select("SELECT g.*, COUNT(gxb.business_id) AS numBusiness FROM game g " +
            "JOIN user_x_game uxg ON g.game_id = uxg.game_id " +
            "JOIN game_x_business gxb ON g.game_id = gxb.game_id " +
            "WHERE user_id = #{userId} " +
            "GROUP BY g.game_id")
    List<Game> selectUsersGames(Integer userId);

    void insertNewGame(Game game);

    @Insert("INSERT INTO user_x_game (user_id, game_id) " +
            "VALUES (#{userId}, #{gameId})")
    void insertGameX(Integer userId, Integer gameId);
}
