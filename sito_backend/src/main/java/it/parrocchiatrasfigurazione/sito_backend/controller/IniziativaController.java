package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        @GetMapping("/iniziative/{id}")
    public String mostraIniziativa(@PathVariable Long id, Model model) {
        model.addAttribute("iniziativa", iniziativaService.getIniziativa(id));
        model.addAttribute("pagina", "iniziative");
        return "iniziative/show";
    }

}