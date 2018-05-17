package com.github.dronezcc.riser.gui.module.base.models.service;

import com.github.dronezcc.riser.gui.module.base.models.domain.Pages;
import com.github.dronezcc.riser.gui.module.base.models.repository.PagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagesService {


    private final PagesRepository pagesRepository;

    public PagesService(@Autowired PagesRepository pagesRepository) {
        this.pagesRepository = pagesRepository;
    }

    public Pages getPage(String p) {
        return pagesRepository.findByPath(p);
    }

    public Pages save(Pages p) {
        return pagesRepository.save(p);
    }

    public List<Pages> getAllPages() {
        List<Pages> pagesList = new ArrayList<>();
        Iterable<Pages> allPages = pagesRepository.findAll();
        allPages.forEach(pagesList::add);
        return pagesList;
    }

    public void deleteById(Integer id) {
        Pages pages = pagesRepository.findOne(id);
        pagesRepository.delete(pages);
    }
}
