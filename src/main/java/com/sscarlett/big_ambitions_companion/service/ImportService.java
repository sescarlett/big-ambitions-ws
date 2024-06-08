package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Import;
import com.sscarlett.big_ambitions_companion.model.ImportSelect;

import java.util.List;

public interface ImportService {

    /**
     * selects a list of  imports
     * @param importSelect game id, importId, dayPerOrderId
     * @return list
     */
    List<Import> getProductValuesPerImport(ImportSelect importSelect);

    /**
     * selects the cross table of importers for a product
     * @param productId id
     * @return list of Int
     */
    List<Integer> getImporterByProduct(Integer productId);
}
