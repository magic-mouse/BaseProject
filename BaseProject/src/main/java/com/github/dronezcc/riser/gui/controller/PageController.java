package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.*;
import com.github.dronezcc.riser.gui.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class PageController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private ReCaptchaService reCaptchaService;
    private UserService userService;
    private UserRoleService userRoleService;
    private ValidatorService validatorService;
    private MailSendingService mailSendingService;
    private MailTokenService mailTokenService;


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


    @RequestMapping(value = "/login/reset")
    public String resetLostPassword(@RequestParam("token") String token){

      if(!mailTokenService.findByToken(token)){
          return "redirect:/404";
      }

      return "login/reset";

    }

    @RequestMapping(value = "/login/validate", method = RequestMethod.POST)
    public String validateLostPassword(@RequestParam("g-recaptcha-response") String lpv, @RequestParam("exampleInputEmail1") String email) {

        String link = "http://localhost:8888/login/reset";

        if (!reCaptchaService.validateService(lpv)) {
            log.error("User could not reset password, captcha was not validated!");
            return "redirect:/login/splash-error";
        }

        if (!validatorService.validatEmail(email)) {
            log.error("User did not submit a valid mail address!");
            return "redirect:/login/splash-error";
        }

        if (!validatorService.emailExists(email)) {
            log.error("Mail address did not exists: " + email);
            return "redirect:/login/splash-error";
        }

        System.out.println(email);
        Long uid = userService.getUserIdFromEmail(email);
        System.out.println(uid);
        Long token = mailTokenService.generateToken(uid);
        System.out.println(token);

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
        String userName = user.getUserName();
        String email = user.getEmail();
        int active = user.getEnabled();

        model.addAttribute("userName", userName);
        model.addAttribute("email", email);
        model.addAttribute("userRole", userRole);
        model.addAttribute("active", active);

        return "user/user";
    }

//

}
