package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {
private final Logger log = LoggerFactory.getLogger(this.getClass());

    MenuRepository menuRepository;

    public MenuService(@Autowired MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }


    public void deleteById(Integer id) {
        menuRepository.delete(id);
    }

    public List<MenuItem> getAll() {
        log.debug("Finds all menuItems");
        List<MenuItem> menuItemList = new ArrayList<>();
        Iterable<MenuItem> menuItems =  menuRepository.findAll();
        menuItemList.forEach(m->{log.debug("LALALALALLALALALALLALALALALLALA " + m.getValue()); menuItemList.add(m);});

        return menuItemList;
    }


    public void save(MenuItem menuItem) {
        menuRepository.save(menuItem);
    }
}
