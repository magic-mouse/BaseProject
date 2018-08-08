package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class MenuService {

    @Autowired
    private Logger log;


    MenuRepository menuRepository;

    public MenuService(@Autowired MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }


    public void deleteById(Integer id) {
        menuRepository.delete(id);
    }

    public List<MenuItem> getAll() {
        Iterable<MenuItem> menuItems;
        List<MenuItem> menuItemList;
        try {
            menuItemList = new ArrayList<>();
            menuItems = menuRepository.findAll();
            menuItems.forEach(m ->
                menuItemList.add(m)
            );
        }catch(Exception e){
            log.info("Error",e);
            return new ArrayList<>();
        }
        return menuItemList;
    }


    public void save(MenuItem menuItem) {
        menuRepository.save(menuItem);
    }

    public MenuItem findById(Integer id) {
        // TODO: Validation, verification
        return menuRepository.findOne(id);
    }
}
