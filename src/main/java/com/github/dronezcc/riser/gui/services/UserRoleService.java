package com.github.dronezcc.riser.gui.services;


import com.github.dronezcc.riser.gui.domain.UserRole;
import com.github.dronezcc.riser.gui.domain.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    UserRolesRepository userRolesRepository;

    public UserRole save(UserRole userRole)
    {
        return userRolesRepository.save(userRole);
    }

    public List<String> findRoleByUserName(String name) {
        return userRolesRepository.findRoleByUserName(name);
    }
}
