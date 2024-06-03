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
    void postNewBusiness(Business business);

    /**
     * inserts products into the business/product cross table
     *
     * @param businessId business id
     * @param productIds list of ids
     */
    void postBusinessProducts(Integer businessId, List<Integer> productIds);
}
