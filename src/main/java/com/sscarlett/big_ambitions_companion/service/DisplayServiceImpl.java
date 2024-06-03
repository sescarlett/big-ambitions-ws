package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.dao.DisplayDao;
import com.sscarlett.big_ambitions_companion.model.Display;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayServiceImpl implements DisplayService {

    @Autowired
    private DisplayDao displayDao;

    /**
     * gets a list of all display types
     *
     * @return list
     */
    @Override
    public List<Display> getAllDisplays() {
        return displayDao.selectAllDisplays();
    }

    /**
     * add new display type
     *
     * @param display info
     */
    @Override
    public void postNewDisplay(Display display) {
        displayDao.insertNewDisplay(display);
    }

    /**
     * update a display info
     *
     * @param display info
     */
    @Override
    public void patchDisplay(Display display) {
        displayDao.updateDisplay(display);
    }
}
