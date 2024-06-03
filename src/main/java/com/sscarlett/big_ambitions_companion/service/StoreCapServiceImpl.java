package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.StoreCapDao;
import com.sscarlett.big_ambitions_companion.model.StoreCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreCapServiceImpl implements StoreCapService {

    @Autowired
    private StoreCapDao storeCapDao;

    /**
     * adds a new store capacity
     *
     * @param storeCap store cap info
     */
    @Override
    public void postNewStoreCap(Integer storeCap) {
        storeCapDao.insertNewStoreCap(storeCap);
    }

    /**
     * gets all store capacities
     *
     * @return list of all caps
     */
    @Override
    public List<StoreCap> getAllStoreCap() {
        return storeCapDao.selectAllStoreCap();
    }

    /**
     * deletes specified store cap
     *
     * @param storeCapId id
     */
    @Override
    public void deleteStoreCap(Integer storeCapId) {
        storeCapDao.deleteStoreCap(storeCapId);
    }
}
