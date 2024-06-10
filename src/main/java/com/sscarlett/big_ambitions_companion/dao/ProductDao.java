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

    @Select("WITH importer_agg AS ( " +
            "    SELECT p.product_id, " +
            "           string_agg(DISTINCT i.name, ', ') AS importerNames " +
            "    FROM product p " +
            "    JOIN public.import_x_product ixp ON p.product_id = ixp.product_id " +
            "    JOIN public.import i ON i.import_id = ixp.import_id " +
            "    GROUP BY p.product_id " +
            "), " +
            "display_agg AS ( " +
            "    SELECT p.product_id, " +
            "           string_agg(DISTINCT d.name, ', ') AS displayNames " +
            "    FROM product p " +
            "    JOIN public.product_x_display pxd ON p.product_id = pxd.product_id " +
            "    JOIN public.display d ON pxd.display_id = d.display_id " +
            "    GROUP BY p.product_id " +
            ") " +
            "SELECT p.*, " +
            "       ia.importerNames, " +
            "       da.displayNames " +
            "FROM product p " +
            "LEFT JOIN importer_agg ia ON p.product_id = ia.product_id " +
            "LEFT JOIN display_agg da ON p.product_id = da.product_id " +
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
