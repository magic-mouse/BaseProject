package com.github.dronezcc.riser.gui;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

//    @RequestMapping(value="/hello", method = RequestMethod.GET)
//    public String printUser(ModelMap model) {
//
//        //User user = (User)
//        System.out.println(      SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        //String name = user.getUsername(); //get logged in username
//
//        //model.addAttribute("username", name);
//        return "hello";
//
//    }
//
//    @RequestMapping(value={"", "/", "home"}, method = RequestMethod.GET)
//    public String showBaseController(){
//        return "home";
//    }

}
