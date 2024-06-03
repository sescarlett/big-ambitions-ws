package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.Display;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DisplayDao {

    @Select("SELECT * FROM display")
    List<Display> selectAllDisplays();

    @Insert("INSERT INTO display (name, cost, customer_cap) " +
            "VALUES (#{name}, #{cost}, #{customerCap})")
    void insertNewDisplay(Display display);

    @Update("UPDATE display " +
            "SET name = #{name}, cost = #{cost}, customer_cap = #{customerCap} " +
            "WHERE display_id = #{displayId}")
    void updateDisplay(Display display);
}
