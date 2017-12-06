package com.github.dronezcc.riser.gui.services;

import com.github.dronezcc.riser.gui.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    @Autowired
    UserService userService;

    public boolean validatEmail(String email) {
        // TODO: Expand this validation!
        if(email.contains("@")){
            return true;
        }


        return false;
    }

    public boolean emailExists(String email) {

        User user = userService.findByEmail(email);

        if(user != null){
            return true;
        }

        return false;

    }
}
