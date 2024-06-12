package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.Import;
import com.sscarlett.big_ambitions_companion.model.ImportSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImportDao {

    @Select("SELECT DISTINCT p.product_id, p.name AS productName, p.value * dpo.days * " +
            "(SELECT SUM(sc.capacity) " +
            " FROM business b " +
            " JOIN game_x_business gxb2 ON b.business_id = gxb2.business_id " +
            " JOIN store_cap sc ON b.size = sc.store_cap_id " +
            " JOIN business_x bx2 ON b.business_id = bx2.business_id " +
            " WHERE gxb2.game_id = #{gameId} AND bx2.product_id = p.product_id" +
            ") AS quantity " +
            "FROM product p " +
            "JOIN import_x_product ixp on p.product_id = ixp.product_id " +
            "JOIN business_x bx on p.product_id = bx.product_id " +
            "JOIN game_x_business gxb on bx.business_id = gxb.business_id " +
            "JOIN day_per_order dpo on dpo.day_per_order_id = #{dayPerOrderId} " +
            "WHERE ixp.import_id = #{importId} AND gxb.game_id = #{gameId}")
    List<Import> selectProductValuesPerImport(ImportSelect importSelect);

    @Select("SELECT import_id FROM import_x_product WHERE product_id = #{productId}")
    List<Integer> selectImporterByProduct(Integer productId);
}
