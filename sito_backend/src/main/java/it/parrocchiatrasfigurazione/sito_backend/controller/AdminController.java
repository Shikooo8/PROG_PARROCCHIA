package it.parrocchiatrasfigurazione.sito_backend.controller;

import it.parrocchiatrasfigurazione.sito_backend.service.EventoService;
import it.parrocchiatrasfigurazione.sito_backend.service.InformazioniGeneraliService;
import it.parrocchiatrasfigurazione.sito_backend.service.IniziativaService;
import it.parrocchiatrasfigurazione.sito_backend.service.NotiziaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.parrocchiatrasfigurazione.sito_backend.model.InformazioniGenerali;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteRichiestaService;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final NotiziaService notiziaService;
    private final EventoService eventoService;
    private final IniziativaService iniziativaService;
    private UtenteRichiestaService utenteRichiestaService;
    private UtenteService utenteService;
    private InformazioniGeneraliService informazioniGeneraliService;

    public AdminController(UtenteRichiestaService utenteRichiestaService, UtenteService utenteService,
            IniziativaService iniziativaService, EventoService eventoService, NotiziaService notiziaService,
            InformazioniGeneraliService informazioniGeneraliService) {
        this.utenteRichiestaService = utenteRichiestaService;
        this.utenteService = utenteService;
        this.iniziativaService = iniziativaService;
        this.eventoService = eventoService;
        this.notiziaService = notiziaService;
        this.informazioniGeneraliService = informazioniGeneraliService;
    }

    @GetMapping("/index")
    public String mostraAmministrazione() {
        return "admin/index";
    }

    // GESTIONE RICHIESTE

    @GetMapping("/richieste")
    public String richieste(Model model) {
        model.addAttribute("richieste", utenteRichiestaService.getTutteLeRichieste());
        model.addAttribute("utente", new Utente());
        return "admin/richieste";
    }

    @PostMapping("/richieste/{id}/cancella")
    public String eliminaRichiesta(@PathVariable Long id) {
        utenteRichiestaService.elimina(id);
        return "redirect:/admin/richieste";
    }

    @PostMapping("/richieste/{id}/approva")
    public String approva(@PathVariable Long id) {
        utenteRichiestaService.approva(id);
        return "redirect:/admin/richieste";
    }

    // GESTIONE EVENTI

    @GetMapping("/eventi")
    public String mostraGestioneEventi(Model model) {
        model.addAttribute("eventi", eventoService.getAll());

        return "admin/eventiList";
    }

    // GESTIONE INIZIATIVE

    @GetMapping("/iniziative")
    public String mostraGestioneIniziative(Model model) {
        model.addAttribute("iniziative", iniziativaService.getAll());
        return "admin/iniziativeList";
    }

    // GESTIONE NOTIZIE

    @GetMapping("/notizie")
    public String mostraGestioneNotizie(Model model) {
        model.addAttribute("notizie", notiziaService.getTutteLeNotizie());
        return "admin/notizieList";
    }

    @GetMapping("/utenti")
    public String mostraGestioneUtenti(Model model) {
        model.addAttribute("utenti", utenteService.getTuttiDaCognome());

        return "admin/utenti";
    }

    @PostMapping("/utenti/{id}/cancella")
    public String deleteUtente(@PathVariable Long id) {
        utenteService.delete(id);
        return "redirect:/admin/utenti";
    }

    @GetMapping("/informazioni-generali")
    public String informazioniGenerali(Model model) {

        model.addAttribute(
                "informazioni",
                informazioniGeneraliService.getInformazioni());

        return "admin/informazioniForm";
    }

    @PostMapping("/informazioni-generali")
    public String salvaInformazioniGenerali(
            @Valid @ModelAttribute("informazioni") InformazioniGenerali informazioni,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/informazioniForm";
        }

        informazioniGeneraliService.save(informazioni);

        return "redirect:/admin/index";
    }

}