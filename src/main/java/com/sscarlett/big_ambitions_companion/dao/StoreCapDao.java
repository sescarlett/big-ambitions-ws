package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.StoreCap;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreCapDao {

    @Insert("INSERT INTO store_cap (capacity) VALUES (#{value})")
    void insertNewStoreCap(Integer storeCap);

    @Select("Select * FROM store_cap")
    List<StoreCap> selectAllStoreCap();

    @Delete("delete FROM store_cap WHERE store_cap_id = #{storeCapId}")
    void deleteStoreCap(Integer storeCapId);
}
