package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Business;
import com.sscarlett.big_ambitions_companion.model.BusinessPlan;

import java.util.List;

public interface BusinessService {

    /**
     * @param businessId business id
     * @return plan
     */
    BusinessPlan getBusinessPlan(Integer businessId);

    /**
     * creates new business
     * @param business info
     */
    void postNewBusiness(Business business, Integer gameId);

    /**
     * inserts products into the business/product cross table
     *
     * @param businessId business id
     * @param productIds list of ids
     */
    void postBusinessProducts(Integer businessId, List<Integer> productIds);

    /**
     * gets all businesses for a game
     * @param gameId id
     * @return list of businesses
     */
    List<Business> getBusinessByGame(Integer gameId);

    /**
     * updates a businesses products
     *
     * @param businessId business id
     * @param products   list of product ids in business
     * @return business plan
     */
    BusinessPlan patchBusinessProducts(Integer businessId, List<Integer> products);
}
