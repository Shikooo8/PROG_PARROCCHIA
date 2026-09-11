package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.parrocchiatrasfigurazione.sito_backend.service.EventoService;
import it.parrocchiatrasfigurazione.sito_backend.service.InformazioniGeneraliService;
import it.parrocchiatrasfigurazione.sito_backend.service.NotiziaService;

@Controller
public class HomeController {

    private final EventoService eventoService;
    private final NotiziaService notiziaService;
    private final InformazioniGeneraliService informazioniGeneraliService;

    public HomeController(EventoService eventoService,
                           NotiziaService notiziaService,
                           InformazioniGeneraliService informazioniGeneraliService) {
        this.eventoService = eventoService;
        this.notiziaService = notiziaService;
        this.informazioniGeneraliService = informazioniGeneraliService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("eventi", eventoService.getEventiVisibiliProssimi());
        model.addAttribute("notizie", notiziaService.getUltimeNotizie());
        model.addAttribute("informazioni", informazioniGeneraliService.getInformazioni());
        return "index";
    }
}