package com.github.dronezcc.riser.gui;

import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.repository.UserRepository;
import com.github.dronezcc.riser.gui.services.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuiApplicationTests {

//    private final static String USER_ADMIN_EMAIL = "admin@localhost";
//    private final static boolean USER_ADMIN_ENABLED = true;
//    private final static String USER_ADMIN_USER_NAME = "admin";
//    private final static String USER_ADMIN_PASSWORD = "qwer1234";
//
//    private UserService userService;
//
//    public GuiApplicationTests(@Autowired UserService userService) {
//        this.userService = userService;
//    }

    @Test
	public void contextLoads() {

//		User u = new User();
//		u.setEmail(USER_ADMIN_EMAIL);
//		u.setEnabled(USER_ADMIN_ENABLED);
//		u.setUserName(USER_ADMIN_USER_NAME);
//		u.setPassword(new BCryptPasswordEncoder().encode(USER_ADMIN_PASSWORD));
//
//        userService.save(u);
	}

}
