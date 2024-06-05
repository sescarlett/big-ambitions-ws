package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductDao {

    @Select("SELECT COALESCE(MAX(product_id) + 1, 1) FROM product")
    Integer selectMaxId();

    @Select("SELECT * FROM product order by name")
    List<Product> selectAllProducts();

    @Insert("INSERT INTO product (product_id, name) VALUES (#{productId}, #{name})")
    void insertNewProduct(Integer productId, String name);

    @Insert("INSERT INTO product_x_display (product_id, display_id, inventory_cap) " +
            "VALUES (#{productId}, #{id}, #{value})")
    void insertDisplayX(Integer productId, IdValue display);
}
