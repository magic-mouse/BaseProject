package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


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

    public User findById(String uid) {
        Long uidl = Long.parseLong(uid);
       return this.findById(uid);
    }

    public User findById(long userId) {
        return userRepository.findOne(userId);
    }
}
