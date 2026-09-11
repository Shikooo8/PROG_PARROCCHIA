package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.parrocchiatrasfigurazione.sito_backend.service.EventoService;

@Controller
public class EventoController {

    private EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/eventi")
    public String list(Model model) {
        model.addAttribute("eventi", eventoService.getEventiVisibili());
        return "eventi/list";
    }
}