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
           "((CEILING(#{businessCap}::numeric / d.customer_cap::numeric)) * " +
           " (select inventory_cap from product_x_display where product_id = p.product_id and display_id = d.display_id )) AS quantity " +
           "FROM product p " +
           "JOIN business_x bx ON p.product_id = bx.product_id " +
           "JOIN display d ON bx.display_id = d.display_id " +
           "WHERE bx.business_id = #{businessId}")
   List<Product> selectProductsByBusiness(Integer businessId, Integer businessCap);

   @Select("SELECT d.*, " +
           "SUM(CEILING(#{businessCap}::numeric / d.customer_cap::numeric)) as quantity " +
           "FROM display d " +
           "JOIN business_x bx ON d.display_id = bx.display_id " +
           "WHERE bx.business_id = #{businessId} " +
           "GROUP BY d.display_id, d.name, d.cost, d.customer_cap")
   List<Display> selectDisplaysByBusiness(Integer businessId, Integer businessCap);

   @Insert("INSERT INTO business (business_id, name, size) VALUES (#{businessId}, #{name}, #{size})")
   void insertNewBusiness(Business business);

   @Select("SELECT product_id as id, display_id as value from business_x " +
           "WHERE business_id = #{businessId}")
   List<IdValue> selectBusinessProducts(Integer businessId);

   @Delete("DELETE FROM business_x " +
           "WHERE business_id = #{businessId} " +
           "AND product_id = #{productId} " +
           "AND display_id = #{displayId}")
   void deleteProductX(Integer businessId, Integer productId, Integer displayId);

   @Insert("INSERT INTO business_x (business_id, display_id, product_id) " +
           "VALUES (#{businessId}, #{displayId}, #{productId} )")
   void insertProductX(Integer businessId, Integer productId, Integer displayId);

   @Select("SELECT b.business_id, b.name, sc.capacity * 5 as size FROM business b " +
           "JOIN game_x_business gxb ON b.business_id = gxb.business_id " +
           "JOIN store_cap sc ON b.size = sc.store_cap_id " +
           "WHERE gxb.game_id = #{gameId}")
   List<Business> selectBusinessByGame(Integer gameId);

   @Insert("INSERT INTO game_x_business (game_id, business_id) " +
           "VALUES (#{gameId}, #{businessId})")
   void insertGameX(Integer gameId, Integer businessId);

   @Select({
           "<script> " +
                   "SELECT product_id as id, display_id as value " +
                   "FROM product_x_display "+
                   "WHERE product_id IN ( " +
                   "SELECT product_id " +
                   "FROM product_x_display " +
                   "WHERE product_id IN " +
                   "<foreach item='item' index='index' collection='productIds' " +
                   "open='(' separator=',' close=')'> " +
                   "#{item} " +
                   "</foreach> " +
                   "GROUP BY product_id " +
                   "HAVING COUNT(display_id) = 1 " +
                   ") " +
                   "</script>"
   })
   List<IdValue> selectUniqueDisplayIds(@Param("productIds") List<Integer> productIds);

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

   @Select("SELECT product_id as id, display_id as value FROM business_x WHERE business_id =#{businessId}")
   List<IdValue> selectDisplayList(Integer businessId);

   @Delete("DELETE FROM business_x where business_id =#{businessId}")
   void deleteProductXAll(Integer businessId);

   @Delete("DELETE FROM game_x_business WHERE business_id = #{businessId}")
   void deleteGameX(Integer businessId);

   @Delete("DELETE FROM business WHERE business_id =#{businessId}")
   void deleteBusiness(Integer businessId);
}
