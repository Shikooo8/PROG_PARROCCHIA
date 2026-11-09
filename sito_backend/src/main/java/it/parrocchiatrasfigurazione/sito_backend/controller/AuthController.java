package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


@Controller 
public class AuthController {


    public AuthController(){
    }

    @GetMapping("/login")
    public String getMethodName(Model model) {
        return "login";
    }
    

}
