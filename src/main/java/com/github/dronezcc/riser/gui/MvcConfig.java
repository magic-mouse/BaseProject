package com.github.dronezcc.riser.gui;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/user/edit").setViewName("hello");
        registry.addViewController("/user/change_password").setViewName("user/change_pass");
        registry.addViewController("/login").setViewName("login");
        // Administration
        registry.addViewController("/admin/users").setViewName("/admin/users");
    }

}