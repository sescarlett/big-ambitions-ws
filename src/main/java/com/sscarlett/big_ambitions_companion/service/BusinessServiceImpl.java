package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.BusinessDao;
import com.sscarlett.big_ambitions_companion.model.Business;
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

    /**
     * creates new business
     *
     * @param business info
     */
    @Override
    public void postNewBusiness(Business business) {
        businessDao.insertNewBusiness(business);
    }

    /**
     * inserts products into the business/product cross table
     *
     * @param businessId business id
     * @param productIds list of ids
     */
    @Override
    public void postBusinessProducts(Integer businessId, List<Integer> productIds) {
        for(Integer i : productIds) {
            businessDao.insertProductX(businessId, i);
        }
    }

    /**
     * gets all businesses for a game
     *
     * @param gameId id
     * @return list of businesses
     */
    @Override
    public List<Business> getBusinessByGame(Integer gameId) {
        return businessDao.selectBusinessByGame(gameId);
    }

    /**
     * updates a businesses products
     *
     * @param businessId business id
     * @param products   list of product ids in business
     */
    @Override
    public void patchBusinessProducts(Integer businessId, List<Integer> products) {
        List<Integer> dbProducts = businessDao.selectBusinessProducts(businessId);

        // Add products that are in the provided list but not in the database
        for (Integer productId : products) {
            if (!dbProducts.contains(productId)) {
                businessDao.insertProductX(businessId, productId);
            }
        }

        // Remove products that are in the database but not in the provided list
        for (Integer dbProductId : dbProducts) {
            if (!products.contains(dbProductId)) {
                businessDao.deleteProductX(businessId, dbProductId);
            }
        }
    }
}
