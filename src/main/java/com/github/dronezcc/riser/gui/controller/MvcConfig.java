package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.module.base.models.Pages;
import com.github.dronezcc.riser.gui.module.base.models.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    PagesService pagesService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/vision").setViewName("vision");
        registry.addViewController("/home").setViewName("hello");
        registry.addViewController("/user/edit").setViewName("hello");
        registry.addViewController("/user/change_password").setViewName("user/change_pass");
        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/login/forgotten").setViewName("login/forgotten");
        registry.addViewController("/login/splash").setViewName("login/splash");
        registry.addViewController("/login/splash-error").setViewName("login/splash-error");
        // Administration
        registry.addViewController("/admin/add").setViewName("/admin/add");
        registry.addViewController("/admin/users").setViewName("/admin/users");

        List<Pages> pagesList =pagesService.getAllPages();

        pagesList.forEach(c -> registry.addViewController(c.getPath()).setViewName("/p/" + c.getTemplate()));


    }

}