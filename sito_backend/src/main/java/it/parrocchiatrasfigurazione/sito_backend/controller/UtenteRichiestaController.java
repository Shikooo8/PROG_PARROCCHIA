package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.parrocchiatrasfigurazione.sito_backend.model.UtenteRichiesta;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteRichiestaService;

@Controller
public class UtenteRichiestaController {

    private UtenteRichiestaService utenteRichiestaService;

    public UtenteRichiestaController(UtenteRichiestaService utenteRichiestaService) {
        this.utenteRichiestaService = utenteRichiestaService;
    }

    @GetMapping("/richiesta-registrazione")
    public String form(Model model) {
        model.addAttribute("richiesta", new UtenteRichiesta());
        return "richiesta-registrazione/form";
    }

    @PostMapping("/richiesta-registrazione")
    public String save(@ModelAttribute("richiesta") UtenteRichiesta richiesta) {
        utenteRichiestaService.save(richiesta);
        return "redirect:/richiesta-registrazione?inviata";
    }
}