package com.github.dronezcc.riser.gui.customers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableWebSecurity
@RequestMapping("/customers")
public class CustomersController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = {"", "/"})
    public String redirectToCustomers(){
        return "redirect:/customers/main/resources/customers";
    }


    @RequestMapping("/main/resources/customers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showCustomer(Model model) {
        model.addAttribute("template", "top");
        return "customers";
    }


    @RequestMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCustomer(Model model)
    {
        log.info("this is it");
        model.addAttribute("page", "this is the content of the \"costumers/add\" page");
        return "templates/customers/add";
    }
}
