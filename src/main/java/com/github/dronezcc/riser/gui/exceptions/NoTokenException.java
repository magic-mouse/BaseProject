package com.github.dronezcc.riser.gui.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NoTokenException extends ResponseEntityExceptionHandler {



}