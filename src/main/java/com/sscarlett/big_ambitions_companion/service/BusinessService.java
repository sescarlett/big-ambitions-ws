package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Business;
import com.sscarlett.big_ambitions_companion.model.BusinessPlan;
import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.model.SingleMultiple;

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
    BusinessPlan patchBusinessProducts(Integer businessId, List<IdValue> products);

    /**
     * gets products and displays separated by singles and multiples
     * @param productIds ids
     * @return list
     */
    SingleMultiple getProductsDisplays(List<Integer> productIds);

    /**
     * selects id value list of a businesses products/displays
     * @param businessId id
     * @return IdValue list
     */
    List<IdValue> selectDisplayList(Integer businessId);
}
