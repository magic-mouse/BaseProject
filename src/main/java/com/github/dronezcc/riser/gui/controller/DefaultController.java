package com.github.dronezcc.riser.gui.controller;

import com.github.dronezcc.riser.gui.domain.Task;
import com.github.dronezcc.riser.gui.module.base.models.domain.Pages;
import com.github.dronezcc.riser.gui.module.base.models.service.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DefaultController {

    @Autowired
    PagesService pagesService;

    @RequestMapping("/")
    public String showFrontpage(Model model) {
         return "module/base/frontpage";
    }

    @RequestMapping("/task/test")
    public String taskTest(Model m){

        Task t = new Task();

        t.setId(1);
        t.setTitle("Not a title");
        t.setText("not a text");
        t.setDueTo("Not a due to");

        List<Task> taskList = new ArrayList<>();

        m.addAttribute("tasks", taskList);
        return "/task/list";
    }



    @RequestMapping("/task/err")
    public String taskErrTest(){
        return "/error";
    }


    @RequestMapping("/p/hello")
    public Pages makePage(){
        Pages p = new Pages();
        p.setAuthor("Me");
        p.setContent("<html><head></head><body><h1>Hello</h1><p>Hello</p></body></html>");
        p.setHeadline("Hello");
        p.setPath("brain");
        return pagesService.save(p);
    }

    @RequestMapping("/p/{p}")
    public String showPage(@PathVariable String p,  Model model) {
        Pages page = pagesService.getPage(p);

        if(page == null){
            return "redirect:/404";
        }

        model.addAttribute("page", page);

        return "module/base/page";
    }


}

