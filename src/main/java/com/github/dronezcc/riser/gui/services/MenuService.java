package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    MenuRepository menuRepository;

    public MenuService(@Autowired MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }


    public void deleteById(Integer id) {
        menuRepository.delete(id);
    }

    public List<MenuItem> getAll() {
        List<MenuItem> menuItemList = new ArrayList<>();
        Iterable<MenuItem> menuItems =  menuRepository.findAll();
        menuItemList.forEach(menuItemList::add);
        return menuItemList;
    }
}
