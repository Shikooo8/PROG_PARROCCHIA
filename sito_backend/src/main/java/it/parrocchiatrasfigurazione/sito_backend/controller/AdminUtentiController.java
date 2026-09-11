package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteRichiestaService;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteService;

@Controller
@RequestMapping("/admin")
public class AdminUtentiController {

    private UtenteRichiestaService utenteRichiestaService;
    private UtenteService utenteService;

    public AdminUtentiController(UtenteRichiestaService utenteRichiestaService, UtenteService utenteService) {
        this.utenteRichiestaService = utenteRichiestaService;
        this.utenteService = utenteService;
    }

    @GetMapping("/richieste")
    public String richieste(Model model) {
        model.addAttribute("richieste", utenteRichiestaService.getTutteLeRichieste());
        model.addAttribute("utente", new Utente());
        return "admin/richieste";
    }

    @PostMapping("/richieste/{id}/elimina")
    public String eliminaRichiesta(@PathVariable Long id) {
        utenteRichiestaService.elimina(id);
        return "redirect:/admin/richieste";
    }

    @PostMapping("/utenti")
    public String registraUtente(@ModelAttribute("utente") Utente utente,
                                  @RequestParam String username,
                                  @RequestParam String password,
                                  Model model) {
        try {
            utenteService.registraUtente(utente, username, password);
            return "redirect:/admin/richieste?registrato";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errore", e.getMessage());
            model.addAttribute("richieste", utenteRichiestaService.getTutteLeRichieste());
            return "admin/richieste";
        }
    }
}