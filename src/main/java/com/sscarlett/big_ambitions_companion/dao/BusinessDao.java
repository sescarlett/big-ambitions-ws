package com.sscarlett.big_ambitions_companion.dao;

import com.sscarlett.big_ambitions_companion.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusinessDao {

   @Select("SELECT COALESCE(MAX(business_id) + 1, 1) FROM business")
   Integer selectMaxId();

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
           "JOIN business_x_display bxd ON d.display_id = pxd.display_id " +
           "WHERE bxd.business_id = #{businessId}" +
           "GROUP BY d.display_id, d.name, d.cost, d.customer_cap")
   List<Display> selectDisplaysByBusiness(Integer businessId, Integer businessCap);

   @Insert("INSERT INTO business (business_id, name, size) VALUES (#{businessId}, #{name}, #{size})")
   void insertNewBusiness(Business business);

   @Select("SELECT product_id from business_x_product WHERE business_id = #{businessId}")
   List<Integer> selectBusinessProducts(Integer businessId);

   @Delete("DELETE FROM business_x_product WHERE business_id = #{businessId} AND product_id = #{productId}")
   void deleteProductX(Integer businessId, Integer productId);

   @Insert("INSERT INTO business_x_product (business_id, product_id) " +
           "VALUES (#{businessId}, #{productId})")
   void insertProductX(Integer businessId, Integer productId);

   @Select("SELECT b.business_id, b.name, sc.capacity * 5 as size FROM business b " +
           "JOIN game_x_business gxb ON b.business_id = gxb.business_id " +
           "JOIN store_cap sc ON b.size = sc.store_cap_id " +
           "WHERE gxb.game_id = #{gameId}")
   List<Business> selectBusinessByGame(Integer gameId);

   @Insert("INSERT INTO game_x_business (game_id, business_id) VALUES (#{gameId}, #{businessId})")
   void insertGameX(Integer gameId, Integer businessId);

   @Select({
           "<script>",
           "SELECT display_id " +
                   "FROM product_x_display "+
                   "WHERE product_id IN (" +
                   "  SELECT product_id" +
                   "  FROM product_x_display" +
                   "  WHERE product_id IN" +
                   "  <foreach item='item' index='index' collection='productIds'" +
                   "    open='(' separator=',' close=')'>" +
                   "    #{item}" +
                   "  </foreach>" +
                   "  GROUP BY product_id" +
                   "  HAVING COUNT(display_id) = 1" +
                   ")" +
                   "</script>"
   })
   List<Integer> selectUniqueDisplayIds(@Param("productIds") List<Integer> productIds);

   @Select({
           "<script>",
           "SELECT pxd.product_id as id, p.name as idName, pxd.display_id as value, d.name as valueName " +
                   "FROM product_x_display pxd " +
                   "JOIN display d ON pxd.display_id = d.display_id "+
                   "JOIN product p ON pxd.product_id = p.product_id "+
                   "WHERE pxd.product_id IN (" +
                   "  SELECT pxd2.product_id" +
                   "  FROM product_x_display pxd2" +
                   "  WHERE pxd2.product_id IN" +
                   "  <foreach item='item' index='index' collection='productIds'" +
                   "    open='(' separator=',' close=')'>" +
                   "    #{item}" +
                   "  </foreach>" +
                   "  GROUP BY pxd2.product_id" +
                   "  HAVING COUNT(pxd2.display_id) > 1" +
                   ")" +
                   "</script>"
   })
   List<IdNameValueName> selectNonUniqueDisplayIds(@Param("productIds") List<Integer> productIds);
}
