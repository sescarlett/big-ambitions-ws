package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

    @Select("SELECT COALESCE(MAX(user_id) + 1, 1) FROM users")
    Integer selectMaxId();

    @Insert("INSERT INTO users (user_id, name, email, password, user_role) " +
            "VALUES (#{userId}, #{name}, #{email}, crypt(#{password}, gen_salt('bf')), 2)")
    void insertNewUser(Users users);

    @Select("SELECT crypt(#{password}, password) = password " +
            "FROM users WHERE email = #{email}")
    Boolean verifyPassword(Users users);

    @Select("SELECT user_id FROM users WHERE email = #{email}")
    Integer selectUserIdByLogin(Users users);

    @Select("SELECT name, email FROM users WHERE user_id = #{userId}")
    Users selectUserById(Integer userId);

    @Update("UPDATE users SET name = #{name}, email = #{email} " +
            "WHERE user_id = #{userId}")
    void updateUser(Users users);

    @Update("UPDATE users set password = crypt(#{password}, gen_salt('bf')) " +
            "WHERE user_id = #{userId}")
    void updatePassword(Users users);
}
