package it.parrocchiatrasfigurazione.sito_backend.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.parrocchiatrasfigurazione.sito_backend.model.Notizia;
import it.parrocchiatrasfigurazione.sito_backend.service.NotiziaService;

@RestController
@RequestMapping("/api/notizie")
public class NotiziaRestController {

    private final NotiziaService notiziaService;

    public NotiziaRestController(NotiziaService notiziaService) {
        this.notiziaService = notiziaService;
    }

    @GetMapping("/{id}")
    public Notizia getNotizia(@PathVariable Long id) {
        Notizia notizia = this.notiziaService.getNotizia(id);
        if (notizia == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notizia non trovata");
        }
        return notizia;
    }
}