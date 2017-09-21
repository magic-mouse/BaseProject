package com.github.dronezcc.riser.gui.module.base.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagesService {

    @Autowired
    PagesRepository pagesRepository;


    public Pages getPage(String p) {
        return pagesRepository.findByPath(p);
    }

    public Pages save(Pages p) {
        return pagesRepository.save(p);
    }
}
