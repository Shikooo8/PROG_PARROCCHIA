package it.parrocchiatrasfigurazione.sito_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.parrocchiatrasfigurazione.sito_backend.exception.DuplicateEventoException;
import it.parrocchiatrasfigurazione.sito_backend.exception.ImmagineNonValidaException;
import it.parrocchiatrasfigurazione.sito_backend.model.Evento;
import it.parrocchiatrasfigurazione.sito_backend.service.EventoService;
import jakarta.validation.Valid;

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

    @GetMapping("/eventi/{id}")
    public String mostraEvento(@PathVariable Long id, Model model) {
        model.addAttribute("evento", eventoService.getEvento(id));
        model.addAttribute("pagina", "eventi");
        return "eventi/show";
    }

    // =========== ADMIN ==============

    @GetMapping("/admin/eventi/nuovo")
    public String showNewEventoForm(Model model) {
        model.addAttribute("evento", new Evento());
        return "admin/eventiForm";
    }

    @PostMapping("/admin/eventi/nuovo")
    public String saveNewEvento(@Valid @ModelAttribute("evento") Evento evento, BindingResult bindingResult,
            @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "admin/eventiForm";
        }
        try {
            eventoService.save(evento, file);
            return "redirect:/admin/eventi";
        } catch (DuplicateEventoException e) {
            bindingResult.reject("evento.duplicate", e.getMessage());
            return "admin/eventiForm";

        } catch (ImmagineNonValidaException e) {
            bindingResult.reject("immagine.nonValida");
            return "admin/eventiForm";
        }
    }

    @GetMapping("/admin/eventi/{id}/modifica")
    public String showExistingEventoForm(@PathVariable Long id, Model model) {
        model.addAttribute("evento", eventoService.getEvento(id));
        return "admin/eventiForm";
    }

    @PostMapping("/admin/eventi/{id}/modifica")
    public String saveExistentEvent(@PathVariable Long id, @Valid @ModelAttribute("evento") Evento evento,
            BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "admin/eventiForm";
        }
        try {
            evento.setId(id);
            this.eventoService.save(evento, file);
            return "redirect:/admin/eventi";

        } catch (DuplicateEventoException e) {
            bindingResult.reject("evento.duplicate");
            return "admin/eventiForm";
        } catch (ImmagineNonValidaException e) {
            bindingResult.reject("immagine.nonValida");
            return "admin/eventiForm";
        }
    }

    @PostMapping("/admin/eventi/{id}/cancella")
    public String deleteEvent(@PathVariable Long id) {
        eventoService.delete(id);
        return "redirect:/admin/eventi";
    }

}