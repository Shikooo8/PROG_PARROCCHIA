package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.parrocchiatrasfigurazione.sito_backend.model.UtenteRichiesta;
import it.parrocchiatrasfigurazione.sito_backend.service.UtenteRichiestaService;
import jakarta.validation.Valid;

@Controller
public class UtenteRichiestaController {

    private UtenteRichiestaService utenteRichiestaService;

    private PasswordEncoder passwordEncoder;

    public UtenteRichiestaController(UtenteRichiestaService utenteRichiestaService, PasswordEncoder passwordEncoder) {
        this.utenteRichiestaService = utenteRichiestaService;
    this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/richiesta-registrazione")
    public String form(Model model) {
        model.addAttribute("richiesta", new UtenteRichiesta());
        return "richiesta";
    }

    @PostMapping("/richiesta-registrazione")
    public String save(@Valid @ModelAttribute("richiesta") UtenteRichiesta richiesta, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "richiesta";
        
        richiesta.setPassword(passwordEncoder.encode(richiesta.getPassword()));
        utenteRichiestaService.save(richiesta);
        return "redirect:/richiesta-registrazione?inviata";
    }
}