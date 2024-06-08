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

    @Select("SELECT g.*, COALESCE(sub.numBusiness, 0) AS numBusiness " +
            "FROM game g " +
            "LEFT JOIN " +
            "(SELECT gxb.game_id, COUNT(gxb.business_id) AS numBusiness " +
            "FROM game_x_business gxb " +
            "GROUP BY gxb.game_id) " +
            "AS sub ON g.game_id = sub.game_id " +
            "JOIN user_x_game uxg ON g.game_id = uxg.game_id " +
            "WHERE uxg.user_id = #{userId}")
    List<Game> selectUsersGames(Integer userId);

    @Insert("INSERT INTO game (game_id, name) values (#{gameId}, #{name})")
    void insertNewGame(Game game);

    @Insert("INSERT INTO user_x_game (user_id, game_id) " +
            "VALUES (#{userId}, #{gameId})")
    void insertGameX(Integer userId, Integer gameId);
}
