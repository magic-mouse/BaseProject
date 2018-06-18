package com.github.dronezcc.riser.gui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
        log.error("Request: {} raised {}", request.getRequestURL(), e.getMessage());
        return new ModelAndView("error");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
        log.error("Request: {} raised {}", request.getRequestURL(), e.getMessage());
        return new ModelAndView("404");
    }
}