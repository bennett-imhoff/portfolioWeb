package com.benimhoff.portfolioWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistasUsuarioController {

    @GetMapping("/")
    public String home (Model model){
        return "home";
    }
}
