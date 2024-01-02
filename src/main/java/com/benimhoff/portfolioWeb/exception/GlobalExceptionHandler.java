package com.benimhoff.portfolioWeb.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginExcepcion.class)
    @ModelAttribute
    public String handleLoginException(LoginExcepcion ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "login";
    }
}
