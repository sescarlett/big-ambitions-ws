package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.ImportDao;
import com.sscarlett.big_ambitions_companion.model.Import;
import com.sscarlett.big_ambitions_companion.model.ImportSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private ImportDao importDao;
    /**
     * selects a list of  imports
     *
     * @param importSelect game id, importId, dayPerOrderId
     * @return list
     */
    @Override
    public List<Import> getProductValuesPerImport(ImportSelect importSelect) {
        return importDao.selectProductValuesPerImport(importSelect);
    }

    /**
     * selects the cross table of importers for a product
     *
     * @param productId id
     * @return list of Int
     */
    @Override
    public List<Integer> getImporterByProduct(Integer productId) {
        return importDao.selectImporterByProduct(productId);
    }
}
