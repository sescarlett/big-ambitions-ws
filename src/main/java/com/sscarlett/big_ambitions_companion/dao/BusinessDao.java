package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.Business;
import com.sscarlett.big_ambitions_companion.model.BusinessPlan;
import com.sscarlett.big_ambitions_companion.model.Display;
import com.sscarlett.big_ambitions_companion.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusinessDao {

   @Select("SELECT business_id, name as businessName, sc.capacity * 5 as businessCap " +
           "FROM business b " +
           "JOIN store_cap sc ON b.size = sc.store_cap_id " +
           "WHERE b.business_id = #{businessId}")
   BusinessPlan selectBusinessPlan(Integer businessId);

   @Select("SELECT p.*, " +
           "((CEILING(#{businessCap}::numeric / d.customer_cap::numeric)) * pxd.inventory_cap) AS quantity " +
           "FROM product p " +
           "JOIN business_x_product bxp ON p.product_id = bxp.product_id " +
           "JOIN product_x_display pxd ON bxp.product_id = pxd.product_id " +
           "JOIN display d ON pxd.display_id = d.display_id " +
           "WHERE bxp.business_id = #{businessId}")
   List<Product> selectProductsByBusiness(Integer businessId, Integer businessCap);

   @Select("SELECT d.*, " +
           "SUM(CEILING(#{businessCap}::numeric / d.customer_cap::numeric)) as quantity " +
           "FROM display d " +
           "JOIN product_x_display pxd ON d.display_id = pxd.display_id " +
           "JOIN business_x_product bxp ON pxd.product_id = bxp.product_id " +
           "WHERE bxp.business_id = #{businessId} " +
           "GROUP BY d.display_id, d.name, d.cost, d.customer_cap")
   List<Display> selectDisplaysByBusiness(Integer businessId, Integer businessCap);

   @Insert("INSERT INTO business (name, size) VALUES (#{name}, #{size})")
   void insertNewBusiness(Business business);

   @Insert("INSERT INTO business_x_product (business_id, product_id) " +
           "VALUES (#{businessId}, #{productId})")
   void insertProductX(Integer businessId, Integer productId);
}
