package com.github.dronezcc.riser.gui.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OpenController {



    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/open")
    public List<String> openApi(){
        List<String> strings = new ArrayList<>();
        strings.add("This");
        strings.add("API");
        strings.add("is");
        strings.add("open");
        return strings;
    }

}
