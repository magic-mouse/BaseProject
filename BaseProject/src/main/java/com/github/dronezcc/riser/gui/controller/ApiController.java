package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.domain.UserRole;
import com.github.dronezcc.riser.gui.model.ApiUser;
import com.github.dronezcc.riser.gui.services.UserRoleService;
import com.github.dronezcc.riser.gui.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {


    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserService userService;
    private UserRoleService userRoleService;


    public ApiController(@Autowired UserService userService, @Autowired UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
    }


    @RequestMapping("/api/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> showUsers() {
        return userService.findAll();
    }

    @RequestMapping("/api/secret_password")
    public void secretPassword(@RequestParam("password") String password, @RequestParam("name") String name) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded_pass = bCryptPasswordEncoder.encode(password);

        User user = userService.findByUserName(name);
        user.setPassword(encoded_pass);
        User us = userService.save(user);
    }


    @RequestMapping(value = "/api/users/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User newUser(@RequestBody ApiUser apiUser) throws Exception {

        log.debug(apiUser.toString());

        User user = new User();
        user.setEmail(apiUser.getEmail());
        user.setUserName(apiUser.getUsername());
        user.setEnabled(apiUser.isActive()?1:0);
        User savedUser = userService.save(user);


        apiUser.getRoles().forEach((key, val) -> {
            if(val.equals("false")) return;

            UserRole ur = new UserRole();
            ur.setUserid(savedUser.getUserid());
            ur.setRole(val);
            userRoleService.save(ur);
        });

        return savedUser;
    }

    @RequestMapping(value = "/api/users/set_password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updatePassword(@RequestParam("oldpassword") String oldPassword, @RequestParam("password") String password) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encoded_pass = bCryptPasswordEncoder.encode(password);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findByUserName(name);

        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(encoded_pass);
            User us = userService.save(user);
            return new ResponseEntity<>(us, HttpStatus.OK);
        } else {
            String json = "[\'message\' : \"User not verified\"]";
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }
}
