package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    Logger log;
    UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository, @Autowired Logger log){
        this.userRepository = userRepository;
        this.log = log;
    }

    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(us -> {us.setPassword(""); userList.add(us);});
        return userList;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long getUserIdFromEmail(String email){
        User uid = userRepository.findByEmail(email);
        return uid.getUserid();
    }

    public User findById(long userId) {
        return userRepository.findOne(userId);
    }
}
