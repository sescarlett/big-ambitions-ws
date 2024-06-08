package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Display;
import com.sscarlett.big_ambitions_companion.model.IdValue;
import com.sscarlett.big_ambitions_companion.service.DisplayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/display", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class DisplayController {

    @Autowired
    private DisplayServiceImpl displayService;

    @GetMapping(value = "/list", produces = "application/json")
    public List<Display> getAllDisplays() {
        return displayService.getAllDisplays();
    }

    @PostMapping(value = "/new")
    public void postNewDisplay(@RequestBody Display display) {
        displayService.postNewDisplay(display);
    }

    @PatchMapping(value = "/update")
    public void patchDisplay(@RequestBody Display display) {
        displayService.patchDisplay(display);
    }

    @GetMapping(value = "/products/{productId}", produces = "application/json")
    public List<IdValue> getDisplaysForProduct(@PathVariable int productId) {
        return displayService.getDisplaysForProduct(productId);
    }
}
