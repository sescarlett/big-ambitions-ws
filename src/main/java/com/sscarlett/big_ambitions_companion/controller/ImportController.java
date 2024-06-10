package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Import;
import com.sscarlett.big_ambitions_companion.model.ImportSelect;
import com.sscarlett.big_ambitions_companion.service.ImportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/import", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class ImportController {

    @Autowired
    private ImportServiceImpl importService;

    @PostMapping(value = "/list", produces = "application/json")
    public List<Import> getProductValuesPerImport(@RequestBody ImportSelect importSelect) {
        return importService.getProductValuesPerImport(importSelect);
    }

    @GetMapping(value = "/product/{productId}", produces = "application/json")
    public List<Integer> getImporterByProduct(@PathVariable Integer productId) {
        return importService.getImporterByProduct(productId);
    }
}
