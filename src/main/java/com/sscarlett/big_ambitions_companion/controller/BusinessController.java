package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.BusinessPlan;
import com.sscarlett.big_ambitions_companion.service.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/business", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class BusinessController {

    @Autowired
    private BusinessServiceImpl businessService;

    @GetMapping(value = "/plan/{businessId}", produces = "application/json")
    public BusinessPlan getBusinessPlan(@PathVariable Integer businessId) {
        return businessService.getBusinessPlan(businessId);
    }
}
