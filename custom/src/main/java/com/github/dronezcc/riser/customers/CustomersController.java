package com.github.dronezcc.riser.customers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableWebSecurity
@RequestMapping("/costumers")
public class CustomersController {

    @RequestMapping("/main/resources/customers")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public String showCustomer(Model model) {
         return "customers/customers";
    }


    @RequestMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCustomer(Model model)
    {

        model.addAttribute("page", "this is the content of the \"costumers/add\" page");
        return "page";
    }
}
