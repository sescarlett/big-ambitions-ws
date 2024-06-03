package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.BusinessPlan;

public interface BusinessService {

    /**
     * @param businessId business id
     * @return plan
     */
    BusinessPlan getBusinessPlan(Integer businessId);
}
