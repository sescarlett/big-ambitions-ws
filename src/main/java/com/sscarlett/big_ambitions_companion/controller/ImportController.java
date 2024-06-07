package com.sscarlett.big_ambitions_companion.controller;

import com.sscarlett.big_ambitions_companion.model.Import;
import com.sscarlett.big_ambitions_companion.model.ImportSelect;
import com.sscarlett.big_ambitions_companion.service.ImportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/import", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class ImportController {

    @Autowired
    private ImportServiceImpl importService;

    @PostMapping(value = "/list", produces = "application/json")
    public List<Import> getProductValuesPerImport(@RequestBody ImportSelect importSelect) {
        log.info("is: " + importSelect);
        return importService.getProductValuesPerImport(importSelect);
    }
}
