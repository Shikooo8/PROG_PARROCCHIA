package it.parrocchiatrasfigurazione.sito_backend.controller;

import it.parrocchiatrasfigurazione.sito_backend.repository.InformazioniGeneraliRepository;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteService;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.parrocchiatrasfigurazione.sito_backend.model.Iniziativa;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.service.CredenzialiService;
import it.parrocchiatrasfigurazione.sito_backend.service.IniziativaService;
import jakarta.validation.Valid;

@Controller
public class IniziativaController {

    private final InformazioniGeneraliRepository informazioniGeneraliRepository;
    private final UtenteService utenteService;
    private IniziativaService iniziativaService;
    private CredenzialiService credenzialiService;

    public IniziativaController(IniziativaService iniziativaService, UtenteService utenteService,
            CredenzialiService credenzialiService, InformazioniGeneraliRepository informazioniGeneraliRepository) {
        this.iniziativaService = iniziativaService;
        this.utenteService = utenteService;
        this.credenzialiService = credenzialiService;
        this.informazioniGeneraliRepository = informazioniGeneraliRepository;
    }

    @GetMapping("/iniziative")
    public String list(Model model) {
        model.addAttribute("iniziative", iniziativaService.getIniziativeVisibili());
        return "iniziative/list";
    }
@GetMapping("/iniziative/{id}")
public String mostraIniziativa(
        @PathVariable Long id,
        Model model,
        Authentication authentication) {

    Iniziativa iniziativa = iniziativaService.getIniziativa(id);

    model.addAttribute("iniziativa", iniziativa);
    model.addAttribute("pagina", "iniziative");

    boolean loggato = authentication != null
            && authentication.isAuthenticated()
            && !(authentication instanceof AnonymousAuthenticationToken);

    model.addAttribute("utenteLoggato", loggato);

    // Valori di default: indispensabili anche per utenti anonimi
    boolean iscritto = false;
    boolean coordinatore = false;

    if (loggato) {

        Utente utente =
                credenzialiService.getUtenteByUsername(authentication.getName());

        iscritto =
                iniziativaService.isUtenteIscritto(id, utente);

        coordinatore =
                iniziativaService.isCoordinatore(id, utente);
    }

    model.addAttribute("iscritto", iscritto);
    model.addAttribute("coordinatore", coordinatore);

    return "iniziative/show";
}


    @PostMapping("/iniziativa/{id}/iscriviti")
    public String iscrizioneUtente(
            @PathVariable Long id,
            Authentication authentication) {

        String username = authentication.getName();

        Utente utente = credenzialiService.getUtenteByUsername(username);

        iniziativaService.addUtenteIscritto(id, utente);

        return "redirect:/iniziative/" + id;
    }

 @PostMapping("/iniziativa/{id}/cancella-iscrizione")
    public String cancellaIscrizioneUtente( @PathVariable Long id,  Authentication authentication) {

        String username = authentication.getName();

        Utente utente = credenzialiService.getUtenteByUsername(username);

        iniziativaService.removeUtenteIscritto(id, utente); //TODO credo bisogni cambiare anche nel'altro senso della relazione

        return "redirect:/iniziative/" + id;
    }

 @PostMapping("/iniziativa/{iniziativaId}/iscritti/{utenteId}/cancella")
    public String cancellaIscrizioneAltroUtente( @PathVariable Long iniziativaId, @PathVariable Long utenteId) {

        Utente utente = utenteService.getUtenteById(utenteId); //TODO odio tutto

        iniziativaService.removeUtenteIscritto(iniziativaId, utente); //TODO credo bisogni cambiare anche nel'altro senso della relazione

        return "redirect:/iniziative/" + iniziativaId;
    }


    // ==================== ADMIN =========================

    @GetMapping("/admin/iniziative/nuovo")
    public String showNewIniziativaForm(Model model) {
        model.addAttribute("iniziativa", new Iniziativa());
        model.addAttribute("utenti", utenteService.getTuttiDaCognome());
        return "admin/iniziativeForm";
    }

    @PostMapping("/admin/iniziative/nuovo")
    public String saveNewIniziativa(@Valid @ModelAttribute("iniziativa") Iniziativa iniziativa,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/iniziativeForm";
        }
        // try{
        iniziativaService.save(iniziativa);
        return "redirect:/admin/iniziative";
        /*
         * } catch(DuplicateIniziativaException e){
         * bindingResult.reject("iniziativa.duplicate", e.getMessage());
         * return "admin/iniziativeForm";
         * 
         * }
         */
    }

    @GetMapping("/admin/iniziative/{id}/modifica")
    public String showExistingIniziativaForm(@PathVariable Long id, Model model) {
        model.addAttribute("iniziativa", iniziativaService.getIniziativa(id));
        model.addAttribute("utenti", utenteService.getTuttiDaCognome());
        return "admin/iniziativeForm";
    }

    @PostMapping("/admin/iniziative/{id}/modifica")
    public String saveExistentEvent(@PathVariable Long id, @Valid @ModelAttribute("iniziativa") Iniziativa iniziativa,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/iniziativeForm";
        }
        // try{
        iniziativa.setId(id);
        this.iniziativaService.save(iniziativa);
        return "redirect:/admin/iniziative";

        /*
         * } catch(DuplicateIniziativaException e){
         * bindingResult.reject("iniziativa.duplicate");
         * return "admin/iniziativeForm";
         * }
         */

    }

    @PostMapping("/admin/iniziative/{id}/cancella")
    public String deleteEvent(@PathVariable Long id) {
        iniziativaService.delete(id);
        return "redirect:/admin/iniziative";
    }

}