package com.github.dronezcc.riser.gui.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    @RequestMapping("/admin/base/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showModuleAdmin(Model model)
    {
        return "/admin/base/admin";
    }
}
