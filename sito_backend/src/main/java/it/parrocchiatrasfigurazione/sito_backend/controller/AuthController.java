package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.parrocchiatrasfigurazione.sito_backend.service.CredenzialiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class AuthController {

    private CredenzialiService credenzialiService;

    public AuthController(CredenzialiService credenzialiService){
        this.credenzialiService= credenzialiService;
    }

    @GetMapping("/login")
    public String getMethodName(Model model) {
        return "login";
    }
    

}
