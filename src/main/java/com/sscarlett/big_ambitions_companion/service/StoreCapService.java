package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.StoreCap;

import java.util.List;

public interface StoreCapService {

    /**
     * adds a new store capacity
     * @param storeCap store cap info
     */
    void postNewStoreCap(Integer storeCap);

    /**
     * gets all store capacities
     *
     * @return list of all caps
     */
    List<StoreCap> getAllStoreCap();

    /**
     * deletes specified store cap
     * @param storeCapId id
     */
    void deleteStoreCap(Integer storeCapId);
}
