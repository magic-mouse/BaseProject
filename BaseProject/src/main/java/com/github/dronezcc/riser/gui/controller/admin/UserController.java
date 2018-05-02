package com.github.dronezcc.riser.gui.controller.admin;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableWebSecurity
@RequestMapping("/admin/user")
public class UserController {

    @RequestMapping("/add")
    public String addUser() {
        return "admin/user/add";
    }

    @RequestMapping(path = {"/", ""})
    public String users() {
        return "admin/user/users";
    }
}
