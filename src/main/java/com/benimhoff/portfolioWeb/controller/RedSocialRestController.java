package com.benimhoff.portfolioWeb.controller;

import com.benimhoff.portfolioWeb.domain.RedSocial;
import com.benimhoff.portfolioWeb.service.RedSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RedSocialRestController {
    
    @Autowired
    RedSocialService redSocialService;

    @PostMapping("/api/redSocial")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void crear(@RequestBody RedSocial redSocial){
        redSocialService.crear(redSocial);
    }
}
