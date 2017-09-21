package com.github.dronezcc.riser.gui.module.base;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String showFrontpage(Model model) {
         return "module/base/frontpage";
    }


    @RequestMapping("/module/base/admin/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showModuleAdmin(Model model)
    {
        return "module/base/admin/admin";
    }
}

