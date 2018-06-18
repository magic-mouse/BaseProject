package com.github.dronezcc.riser.gui.controller.admin;

import com.github.dronezcc.riser.gui.components.ProductBreadcrumbBuilder;
import com.github.dronezcc.riser.gui.domain.Breadcrumb;
import com.github.dronezcc.riser.gui.domain.MenuItem;
import com.github.dronezcc.riser.gui.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(@Autowired MenuService menuService){
        this.menuService = menuService;
    }

    @RequestMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showModuleAdmin(Model model, HttpServletRequest request)
    {

        List<MenuItem> menuData = menuService.getAll();
        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("menuData", menuData);
        model.addAttribute("breadCrumb", collect );
        return "/admin/menu/admin";
    }

    @RequestMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPageAdmin(Model model, HttpServletRequest request)
    {
        List<Breadcrumb> collect = new ProductBreadcrumbBuilder().getBreadcrumbs(request.getServletPath());
        model.addAttribute("breadCrumb", collect );
        return "/admin/menu/add";
    }

    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePageAdmin(Model model, HttpServletRequest request, @PathVariable Integer id) {

        menuService.deleteById(id);

        return new ResponseEntity<>("{\"post_deleted\": " + id +")", HttpStatus.OK);
    }
}





