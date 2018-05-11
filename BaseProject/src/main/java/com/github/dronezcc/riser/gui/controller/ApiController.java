package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.domain.ResponseBase;
import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.domain.UserRole;
import com.github.dronezcc.riser.gui.model.ApiUser;
import com.github.dronezcc.riser.gui.module.base.models.domain.Pages;
import com.github.dronezcc.riser.gui.module.base.models.service.PagesService;
import com.github.dronezcc.riser.gui.services.MenuService;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {


    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PagesService pagesService;
    private final MenuService menuService;


    public ApiController(@Autowired UserService userService,
                         @Autowired UserRoleService userRoleService,
                         @Autowired PagesService pagesService,
                         @Autowired MenuService menuService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.pagesService = pagesService;
        this.menuService = menuService;
    }


    @RequestMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> showUsers() {
        return userService.findAll();
    }

    @RequestMapping("/secret_password")
    public void secretPassword(@RequestParam("password") String password, @RequestParam("name") String name) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded_pass = bCryptPasswordEncoder.encode(password);

        User user = userService.findByUserName(name);
        user.setPassword(encoded_pass);
        User us = userService.save(user);
    }


    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> newUser(@RequestBody ApiUser apiUser) {

        ObjectMapper mapper = new ObjectMapper();

        log.debug(apiUser.toString());

        User user = new User();
        if (apiUser.getId() != null) {
            user.setUserid(apiUser.getId());
        }
        user.setEmail(apiUser.getEmail());
        user.setUserName(apiUser.getUsername());
        user.setEnabled(apiUser.isActive() ? 1 : 0);

        User savedUser;

        try {
            savedUser = userService.save(user);
        } catch (Exception e) {
            log.error("Saved somthing wrong", e);
            return new ResponseEntity<String>("{message: \"there was an error\", stacktrace: \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
        }
        if (apiUser.getRoles() != null && apiUser.getRoles().size() > 0) {
            apiUser.getRoles().forEach((key, val) -> {
                if (val.equals("false")) return;
                UserRole ur = new UserRole();
                ur.setUserid(savedUser.getUserid());
                ur.setRole(val);
                userRoleService.save(ur);
            });
        }


            return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/set_password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updatePassword(@RequestParam("oldpassword") String oldPassword, @RequestParam("password") String password) {
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

    @RequestMapping(value = "/base", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateBase() {
        List<Pages> pagesList = pagesService.getAllPages();
        return new ResponseEntity<>(pagesList, HttpStatus.OK);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getMenu() {
        List<MenuItem> pagesList = menuService.getAll();
        return new ResponseEntity<>(pagesList, HttpStatus.OK);
    }

    @RequestMapping(value = "/base/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateBase(@RequestBody ResponseBase responseBase) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Pages page = new Pages();

        page.setContent(responseBase.getContent());
        page.setPath(responseBase.getPath());
        page.setHeadline(responseBase.getPage_name());
        page.setAuthor(name);
        Pages pReturn = pagesService.save(page);


        return new ResponseEntity<>(pReturn, HttpStatus.OK);

    }
}
