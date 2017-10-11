package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.*;
import com.github.dronezcc.riser.gui.services.ReCaptchaService;
import com.github.dronezcc.riser.gui.services.UserRoleService;
import com.github.dronezcc.riser.gui.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PageController {




    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReCaptchaService reCaptchaService;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;



    @RequestMapping(value = "/login/validate", method = RequestMethod.POST)
    public String validateLostPassword(@RequestParam("g-recaptcha-response") String lpv ){

            if(!reCaptchaService.validateService(lpv)){
                log.error("User could not reset password, captcha was not validated!");
                return "redirect:/login/splash-error";
            }




        log.info("Send email with login information");
        return "redirect:/login/splash";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showUser(Model model){

        User user = null;
        List<String> userRole = null;
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userService.findByUserName(name);

            userRole = userRoleService.findRoleByUserName(name);

        }catch(Exception err){
            log.error("could not find logged in user!");
        }
        String userName = user.getUserName();
        String email = user.getEmail();
        int active = user.getEnabled();

        model.addAttribute("userName", userName);
        model.addAttribute("email", email);
        model.addAttribute("userRole", userRole);
        model.addAttribute("active", active);

        return "user/user";
    }

    @RequestMapping("/admin/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminUser(@RequestParam(value="name") String name,  Model model){

        User user = null;
        List<String> userRole = null;
        try {
            user = userService.findByUserName(name);
            userRole = userRoleService.findRoleByUserName(name);
        }catch(Exception err){
            log.error("could not find logged in user!");
        }
        String userName = user.getUserName();
        String email = user.getEmail();
        int active = user.getEnabled();



        model.addAttribute("userName", userName);
        model.addAttribute("email", email);
        model.addAttribute("userRole", userRole);
        model.addAttribute("active", active);

        return "user/user";
    }

}
