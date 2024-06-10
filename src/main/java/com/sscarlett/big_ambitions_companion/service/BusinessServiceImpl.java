package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.BusinessDao;
import com.sscarlett.big_ambitions_companion.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    public void postNewBusiness(Business business, Integer gameId) {
        Integer newId = businessDao.selectMaxId();
        log.info("newId: " + newId);
        business.setBusinessId(newId);
//        log.info("business: " + business);
        businessDao.insertNewBusiness(business);
        businessDao.insertGameX(gameId, newId);

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
     * @return business plan
     */
    @Override
    public BusinessPlan patchBusinessProducts(Integer businessId, List<Integer> products) {
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

        BusinessPlan bp = businessDao.selectBusinessPlan(businessId);
        List<Product> p = businessDao.selectProductsByBusiness(businessId, bp.getBusinessCap());
        List<Display> d = businessDao.selectDisplaysByBusiness(businessId, bp.getBusinessCap());
        bp.setProductList(p);
        bp.setDisplayList(d);
        //get new business plan
        return bp;
    }

    /**
     * gets products and displays separated by singles and multiples
     *
     * @param productIds ids
     * @return list
     */
    @Override
    public SingleMultiple getProductsDisplays(List<Integer> productIds) {
        List<Integer> s = businessDao.selectUniqueDisplayIds(productIds);
        List<IdNameValueName> m = businessDao.selectNonUniqueDisplayIds(productIds);
        SingleMultiple sm = new SingleMultiple();
        sm.setSingles(s);
        sm.setMultiples(m);
        return sm;
    }
}
