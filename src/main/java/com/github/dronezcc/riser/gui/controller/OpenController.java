package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OpenController {

    @Autowired
    MenuService menuService;


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/open")
    public List<String> openApi(){
        List<String> strings = new ArrayList<>();
        strings.add("This");
        strings.add("API");
        strings.add("is");
        strings.add("open");
        return strings;
    }

    @RequestMapping("/addmenu")
    public void addMenu(){
        MenuItem menuItem = new MenuItem();
        menuItem.setLocation("//runt");
        menuItem.setValue("HelloThis");
        menuItem.setWeight(2);

        menuService.save(menuItem);

    }

}
