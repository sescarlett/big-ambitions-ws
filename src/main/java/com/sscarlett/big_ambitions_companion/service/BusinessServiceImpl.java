package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.BusinessDao;
import com.sscarlett.big_ambitions_companion.model.*;
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
    public void postNewBusiness(Business business, Integer gameId) {
        Integer newId = businessDao.selectMaxId();
        business.setBusinessId(newId);
        businessDao.insertNewBusiness(business);
        businessDao.insertGameX(gameId, newId);

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
     * @param productDisplays   list of product ids in business
     * @return business plan
     */
    @Override
    public BusinessPlan patchBusinessProducts(Integer businessId, List<IdValue> productDisplays) {
        List<IdValue> dbProducts = businessDao.selectBusinessProducts(businessId);

        if(!productDisplays.isEmpty()){ // Add products that are in the provided list but not in the database
            for (IdValue pd : productDisplays) {
                if (!dbProducts.contains(pd)) {
                    businessDao.insertProductX(businessId, pd.getId(), pd.getValue());
                }
            }

            // Remove products that are in the database but not in the provided list
            for (IdValue db : dbProducts) {
                if (!productDisplays.contains(db)) {
                    businessDao.deleteProductX(businessId, db.getId(), db.getValue());
                }
            }
        } else businessDao.deleteProductXAll(businessId);

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
        List<IdValue> s = businessDao.selectUniqueDisplayIds(productIds);
        List<IdNameValueName> m = businessDao.selectNonUniqueDisplayIds(productIds);
        SingleMultiple sm = new SingleMultiple();
        sm.setSingles(s);
        sm.setMultiples(m);
        return sm;
    }

    /**
     * selects id value list of a businesses products/displays
     *
     * @param businessId id
     * @return IdValue list
     */
    @Override
    public List<IdValue> selectDisplayList(Integer businessId) {
        return businessDao.selectDisplayList(businessId);
    }

    /**
     * deletes a business
     *
     * @param businessId id
     */
    @Override
    public void deleteBusiness(Integer businessId) {
        businessDao.deleteGameX(businessId);
        businessDao.deleteProductXAll(businessId);
        businessDao.deleteBusiness(businessId);
    }
}
