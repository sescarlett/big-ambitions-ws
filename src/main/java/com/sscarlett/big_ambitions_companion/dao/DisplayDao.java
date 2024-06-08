package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.Display;
import com.sscarlett.big_ambitions_companion.model.IdValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DisplayDao {

    @Select("SELECT * FROM display ORDER BY name")
    List<Display> selectAllDisplays();

    @Insert("INSERT INTO display (name, cost, customer_cap) " +
            "VALUES (#{name}, #{cost}, #{customerCap})")
    void insertNewDisplay(Display display);

    @Update("UPDATE display " +
            "SET name = #{name}, cost = #{cost}, customer_cap = #{customerCap} " +
            "WHERE display_id = #{displayId}")
    void updateDisplay(Display display);

    @Select("SELECT display_id as id, inventory_cap as value from product_x_display WHERE product_id = #{productId}")
    List<IdValue> selectDisplaysForProduct(int productId);
}
