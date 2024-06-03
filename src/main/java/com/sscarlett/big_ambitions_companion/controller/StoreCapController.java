package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.model.StoreCap;
import com.sscarlett.big_ambitions_companion.service.StoreCapServiceImpl;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/store-cap", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class StoreCapController {

    @Autowired
    private StoreCapServiceImpl storeCapService;

    @PostMapping(value = "/new")
    public void postNewStoreCap(@RequestBody Integer storeCap) {
        storeCapService.postNewStoreCap(storeCap);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public List<StoreCap> getAllStoreCap() {
        return storeCapService.getAllStoreCap();
    }

    @DeleteMapping(value = "/delete/{storeCapId}")
    public void deleteStoreCap(@PathVariable Integer storeCapId) {
        storeCapService.deleteStoreCap(storeCapId);
    }
}
