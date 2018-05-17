package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    private final UserService userService;

    public ValidatorService(@Autowired UserService userService ){
        this.userService = userService;
    }

    public boolean validatEmail(String email) {
        // TODO: Expand this validation!
        return email.contains("@");
    }

    public boolean emailExists(String email) {
        User user = userService.findByEmail(email);
        return user != null;
    }
}
