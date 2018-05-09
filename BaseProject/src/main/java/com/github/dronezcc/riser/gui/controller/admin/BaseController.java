package com.github.dronezcc.riser.gui.controller.admin;

import com.github.dronezcc.riser.gui.components.ProductBreadcrumbBuilder;
import com.github.dronezcc.riser.gui.domain.Breadcrumb;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/base")
public class BaseController {
    @RequestMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showModuleAdmin(Model model, HttpServletRequest request)
    {
        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("breadCrumb", collect );
        return "/admin/base/admin";
    }

    @RequestMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPageAdmin(Model model, HttpServletRequest request)
    {
        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("breadCrumb", collect );
        return "/admin/base/add";
    }
}





