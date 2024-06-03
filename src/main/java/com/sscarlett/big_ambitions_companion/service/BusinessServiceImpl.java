package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.BusinessDao;
import com.sscarlett.big_ambitions_companion.model.BusinessPlan;
import com.sscarlett.big_ambitions_companion.model.Display;
import com.sscarlett.big_ambitions_companion.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    /**
     * @param businessId business id
     * @return plan
     */
    @Override
    public BusinessPlan getBusinessPlan(Integer businessId) {
        BusinessPlan plan = businessDao.selectBusinessPlan(businessId);
        List<Product> products = businessDao.selectProductsByBusiness(businessId, plan.getBusinessCap());
        List<Display> displays = businessDao.selectDisplaysByBusiness(businessId, plan.getBusinessCap());
        plan.setProductList(products);
        plan.setDisplayList(displays);
        return plan;
    }
}
