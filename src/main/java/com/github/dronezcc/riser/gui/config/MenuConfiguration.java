package com.github.dronezcc.riser.gui.config;


import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.services.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MenuConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    MenuService menuService;

    public MenuConfiguration(@Autowired MenuService menuService){
        this.menuService = menuService;
    }

    @Bean(name = "messages")
    public List<MenuItem> messages() {
        List<MenuItem> menuItems = menuService.getAll();
        log.debug("MenuItems " + menuItems.size());
        return menuItems;
    }
}
