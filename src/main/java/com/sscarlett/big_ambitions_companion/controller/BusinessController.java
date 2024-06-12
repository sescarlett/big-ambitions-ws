package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Business;
import com.sscarlett.big_ambitions_companion.model.BusinessPlan;
import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.model.SingleMultiple;
import com.sscarlett.big_ambitions_companion.service.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/business", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class BusinessController {

    @Autowired
    private BusinessServiceImpl businessService;

    @GetMapping(value = "/plan/{businessId}", produces = "application/json")
    public BusinessPlan getBusinessPlan(@PathVariable Integer businessId) {
        return businessService.getBusinessPlan(businessId);
    }

    @PostMapping(value = "/new/{gameId}")
    public void postNewBusiness(@RequestBody Business business, @PathVariable Integer gameId) {
        businessService.postNewBusiness(business, gameId);
    }

    @GetMapping(value = "/list/{gameId}", produces = "application/json")
    public List<Business> getBusinessByGame(@PathVariable Integer gameId) {
        return businessService.getBusinessByGame(gameId);
    }

    @PatchMapping(value = "/update/products/{businessId}")
    public BusinessPlan patchBusinessProducts( @PathVariable Integer businessId, @RequestBody List<IdValue> productDisplays) {
        return businessService.patchBusinessProducts(businessId, productDisplays);
    }

    @PostMapping(value = "/products/list", produces = "application/json")
    public SingleMultiple getProductsDisplays(@RequestBody List<Integer> productIds) {
        return businessService.getProductsDisplays(productIds);
    }

    @GetMapping(value = "/displays/list/{businessId}", produces = "application/json")
    public List<IdValue> selectDisplayList(@PathVariable Integer businessId) {
        return businessService.selectDisplayList(businessId);
    }

    @DeleteMapping(value="/delete/{businessId}")
    public void deleteBusiness(@PathVariable Integer businessId) {
        businessService.deleteBusiness(businessId);
    }
}
