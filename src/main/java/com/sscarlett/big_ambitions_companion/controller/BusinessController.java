package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Business;
import com.sscarlett.big_ambitions_companion.model.BusinessPlan;
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

    @PostMapping(value = "/new")
    public void postNewBusiness(@RequestBody Business business) {
        businessService.postNewBusiness(business);
    }

    @PostMapping(value = "/products/{businessId}")
    public void postBusinessProducts(@RequestBody List<Integer> productIds, @PathVariable Integer businessId) {
        businessService.postBusinessProducts(businessId, productIds);
    }

    @GetMapping(value = "/list/{gameId}", produces = "application/json")
    public List<Business> getBusinessByGame(@PathVariable Integer gameId) {
        return businessService.getBusinessByGame(gameId);

    }
}
