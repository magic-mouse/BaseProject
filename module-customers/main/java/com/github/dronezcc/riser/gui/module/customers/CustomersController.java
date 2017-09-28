package com.github.dronezcc.riser.gui.module.customers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomersController {

    @RequestMapping("/customers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showCustomer(Model model) {
         return "module/customers/customers";
    }
    @RequestMapping("/customers/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCustomer(Model model) {
        return "module/customers/add";
    }
}
