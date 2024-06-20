package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.model.Product;
import com.sscarlett.big_ambitions_companion.model.ProductList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductDao {

    @Select("SELECT COALESCE(MAX(product_id) + 1, 1) FROM product")
    Integer selectMaxId();

    @Select("SELECT p.product_id, p.name, p.cost, " +
            "(SELECT GROUP_CONCAT(DISTINCT i.name SEPARATOR ', ') " +
            " FROM import_x_product ixp " +
            " JOIN import i ON i.import_id = ixp.import_id " +
            " WHERE ixp.product_id = p.product_id) AS importerNames, " +
            "(SELECT GROUP_CONCAT(DISTINCT d.name SEPARATOR ', ') " +
            " FROM product_x_display pxd " +
            " JOIN display d ON d.display_id = pxd.display_id " +
            " WHERE pxd.product_id = p.product_id) AS displayNames " +
            "FROM product p " +
            "ORDER BY p.name")
    List<ProductList> selectAllProducts();

    @Insert("INSERT INTO product (product_id, name, value, cost) VALUES (#{productId}, #{name}, #{value}, #{cost})")
    void insertNewProduct(Product product);

    @Insert("INSERT INTO product_x_display (product_id, display_id, inventory_cap) " +
            "VALUES (#{productId}, #{displayId}, #{inventoryCap})")
    void insertDisplayX(Integer productId, Integer displayId, Integer inventoryCap);

    @Insert("INSERT INTO import_x_product (import_id, product_id) VALUES (#{importId}, #{productId})")
    void insertImportX(Integer importId, Integer productId);

    @Update("UPDATE product SET name = #{name}, value = #{value}, cost = #{cost} WHERE product_id = #{productId}")
    void updateProduct(Product product);

    @Select("SELECT display_id as id, inventory_cap as value FROM product_x_display WHERE product_id = #{productId}")
    List<IdValue> selectDisplayX(Integer productId);

    @Select("SELECT import_id from import_x_product WHERE product_id = #{productId}")
    List<Integer> selectImportX(Integer productId);

    @Delete("DELETE FROM product_x_display WHERE display_id = #{displayId} AND product_id = #{productId}")
    void removeDisplayX(Integer productId, Integer displayId);

    @Delete("DELETE FROM import_x_product WHERE import_id = #{importId} AND product_id = #{productId}")
    void removeImportX(Integer importId, Integer productId);

    @Update("UPDATE product_x_display SET inventory_cap = #{inventoryCap} WHERE product_id = #{productId} AND display_id = #{displayId}")
    void updateDisplayX(Integer productId, Integer displayId, Integer inventoryCap);

    @Delete("DELETE FROM product_x_display WHERE product_id = #{productId}")
    void removeAllDisplays(Integer productId);

    @Delete("DELETE FROM import_x_product WHERE product_id = #{productId}")
    void removeAllImports(Integer productId);
}
