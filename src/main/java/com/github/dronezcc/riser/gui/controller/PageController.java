package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.domain.UserRepository;
import com.github.dronezcc.riser.gui.domain.UserRole;
import com.github.dronezcc.riser.gui.domain.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PageController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;

    /*
    @RequestMapping("/backdoor")
    @ResponseBody
    public String addUser(Model model, @RequestParam(value="key") String key){

        if(!key.equals("secretKey")){return "";}

        BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
        String encoded_pass = bCryptPasswordEncoder.encode("Admin");

        User user = new User();
        user.setUserName("Admin");
        user.setEnabled(1);
        user.setEmail("admin@admin.ocm");
        user.setPassword(encoded_pass);

        User saved = userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_ADMIN");
        userRole.setUserid(saved.getUserid());
        userRolesRepository.save(userRole);

        UserRole userRole1 = new UserRole();
        userRole1.setUserid(saved.getUserid());
        userRole1.setRole("ROLE_USER");
        userRolesRepository.save(userRole1);

        return "SUCCESS!";
    }
     */

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showUser(Model model){

        User user = null;
        List<String> userRole = null;
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userRepository.findByUserName(name);

            userRole = userRolesRepository.findRoleByUserName(name);

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
            user = userRepository.findByUserName(name);
            userRole = userRolesRepository.findRoleByUserName(name);
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
