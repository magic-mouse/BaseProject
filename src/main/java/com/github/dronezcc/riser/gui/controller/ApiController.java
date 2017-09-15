package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {


    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/api/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> showUsers(){
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(us -> {us.setPassword(""); userList.add(us);});

        return userList;
    }


    @RequestMapping(value = "/api/users/set_password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public User updatePassword(@RequestParam("password") String password){
        BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();

        String encoded_pass = bCryptPasswordEncoder.encode(password);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUserName(name);

        user.setPassword(encoded_pass);

        User us = userRepository.save(user);

        return us;
    }



}
