package com.sscarlett.big_ambitions_companion.service;

import com.sscarlett.big_ambitions_companion.model.Display;

import java.util.List;

public interface DisplayService {

    /**
     * gets a list of all display types
     * @return list
     */
    List<Display> getAllDisplays();

    /**
     * add new display type
     * @param display info
     */
    void postNewDisplay(Display display);

    /**
     * update a display info
     * @param display info
     */
    void patchDisplay(Display display);
}
