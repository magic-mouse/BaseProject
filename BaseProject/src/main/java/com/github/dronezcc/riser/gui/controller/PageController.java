package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.*;
import com.github.dronezcc.riser.gui.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.util.*;

@Controller
public class PageController {

    @Value("${server.port}")
    int serverPort;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ReCaptchaService reCaptchaService;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final ValidatorService validatorService;
    private final MailSendingService mailSendingService;
    private final MailTokenService mailTokenService;


    public PageController(@Autowired ReCaptchaService reCaptchaService,
                          @Autowired UserService userService,
                          @Autowired UserRoleService userRoleService,
                          @Autowired ValidatorService validatorService,
                          @Autowired MailSendingService mailSendingService,
                          @Autowired MailTokenService mailTokenService
    ){
        this.reCaptchaService = reCaptchaService;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.validatorService = validatorService;
        this.mailSendingService = mailSendingService;
        this.mailTokenService = mailTokenService;
    }

    @RequestMapping(value = "/login/reset/save")
    public String saveLostPassword(Model model, @RequestParam("id") String token, @RequestParam("password") String password){

        Token token1 = mailTokenService.findTokenById(token);
        User user = userService.findById(token1.getUserId());
        token1.setUsed(true);

        mailTokenService.save(token1);

        user.setPassword(new BCryptPasswordEncoder().encode(password));

        userService.save(user);

        return "redirect:/login";
    }


    @RequestMapping(value = "/login/reset")
    public String resetLostPassword(Model model, @RequestParam("token") String token){

        if(!mailTokenService.isTokenValid(token)){
          log.error("The provided token is not valid");
          return "redirect:/login/splash-error?q=invalid%20token";
        }

        model.addAttribute("id", token);
        return "login/reset";
    }

    @RequestMapping(value = "/login/validate", method = RequestMethod.POST)
    public String validateLostPassword(@RequestParam("g-recaptcha-response") String lpv, @RequestParam("exampleInputEmail1") String email) {



        String link = "http://localhost:" + serverPort + "/login/reset";

        if (!reCaptchaService.validateService(lpv)) {
            log.error("User could not reset password, captcha was not validated!");
            return "redirect:/login/splash-error?q=invalid%20captcha";
        }

        if (!validatorService.validatEmail(email)) {
            log.error("User did not submit a valid mail address!");
            return "redirect:/login/splash-error?q=unidentified";
        }

        if (!validatorService.emailExists(email)) {
            log.error("Mail address did not exists: " + email);
            return "redirect:/login/splash-error?q=unidentified";
        }

        log.debug("this is email {}", email);
        Long uid = userService.getUserIdFromEmail(email);
        UUID token = mailTokenService.generateToken(uid);

        mailSendingService.sendPasswordResetMail(email, link, token.toString());
        log.info("Send email with login information");
        return "redirect:/login/splash";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String showUser(Model model) {
        User user = null;
        List<String> userRole = null;

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            String name = auth.getName();

            user = userService.findByUserName(name);
            userRole = userRoleService.findRoleByUserName(name);
        } catch (Exception err) {
            log.error("could not find logged in user!");
        }

        model.addAttribute("uid", user.getUserid());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("userRole", userRole);
        model.addAttribute("active", user.getEnabled());

        return "user/user";
    }

    @RequestMapping("/car")
    public String openCar(){
        return "redirect:/home";
    }

//

}
