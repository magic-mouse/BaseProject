package com.github.dronezcc.riser.gui.controller.admin;

import com.github.dronezcc.riser.gui.components.ProductBreadcrumbBuilder;
import com.github.dronezcc.riser.gui.domain.Breadcrumb;
import com.github.dronezcc.riser.gui.domain.User;
import com.github.dronezcc.riser.gui.services.UserRoleService;
import com.github.dronezcc.riser.gui.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@EnableWebSecurity
@RequestMapping("/admin/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final UserRoleService  userRoleService;

    public UserController(@Autowired UserService userService, @Autowired UserRoleService userRoleService){
        this.userService = userService;
        this.userRoleService = userRoleService;
    }


    @RequestMapping("/add")
    public String addUser(Model model, HttpServletRequest request) {
        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("breadCrumb", collect );
        return "admin/user/add";
    }

    @RequestMapping(path = {"/", ""})
    public String users(Model model, HttpServletRequest request) {
        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("breadCrumb", collect );
        return "admin/user/users";
    }

    @RequestMapping("/{name}")
    public String showUser(@PathVariable("name") String name, Model model, HttpServletRequest request) {

        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("breadCrumb", collect );

        User user = null;
        List<String> userRole = null;
        try {
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
}
