package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.parrocchiatrasfigurazione.sito_backend.service.NotiziaService;

@Controller
public class NotiziaController {

    private NotiziaService notiziaService;

    public NotiziaController(NotiziaService notiziaService) {
        this.notiziaService = notiziaService;
    }

    @GetMapping("/notizie")
    public String list(Model model) {
        model.addAttribute("notizie", notiziaService.getTutteLeNotizie());
        return "notizie/list";
    }
}