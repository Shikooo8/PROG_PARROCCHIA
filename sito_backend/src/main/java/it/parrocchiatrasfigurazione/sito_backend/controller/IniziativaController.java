package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.parrocchiatrasfigurazione.sito_backend.service.IniziativaService;

@Controller
public class IniziativaController {

    private IniziativaService iniziativaService;

    public IniziativaController(IniziativaService iniziativaService) {
        this.iniziativaService = iniziativaService;
    }

    @GetMapping("/iniziative")
    public String list(Model model) {
        model.addAttribute("iniziative", iniziativaService.getIniziativeVisibili());
        return "iniziative/list";
    }
}