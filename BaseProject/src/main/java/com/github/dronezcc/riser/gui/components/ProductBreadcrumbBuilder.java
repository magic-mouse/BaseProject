package com.github.dronezcc.riser.gui.components;

import com.github.dronezcc.riser.gui.domain.Breadcrumb;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProductBreadcrumbBuilder {
    public List<Breadcrumb> getBreadcrumbs(String path) throws IllegalArgumentException {
        String subPath = "/";
        String[] breadCrumbs =  path.split("/");

        List<Breadcrumb> breadcrumbs = new ArrayList<>();

        for (String str : breadCrumbs){
            if(str == null || str.equals("")){
                continue;
            }
            subPath = subPath.concat(str.concat("/"));
            breadcrumbs.add(new Breadcrumb(subPath , str));
        }
        return breadcrumbs;
    }
}
