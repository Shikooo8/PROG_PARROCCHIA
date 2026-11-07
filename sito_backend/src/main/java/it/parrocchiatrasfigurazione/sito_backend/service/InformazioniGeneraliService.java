package it.parrocchiatrasfigurazione.sito_backend.service;

import it.parrocchiatrasfigurazione.sito_backend.model.InformazioniGenerali;
import it.parrocchiatrasfigurazione.sito_backend.repository.InformazioniGeneraliRepository;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InformazioniGeneraliService {

    private final InformazioniGeneraliRepository informazioniGeneraliRepository;

    public InformazioniGeneraliService(InformazioniGeneraliRepository informazioniGeneraliRepository) {
        this.informazioniGeneraliRepository = informazioniGeneraliRepository;
    }

    @Transactional(readOnly = true)
    public InformazioniGenerali getInformazioni() {
        return informazioniGeneraliRepository.findFirstByOrderByIdAsc().orElse(null);
    }

    @Transactional
    public InformazioniGenerali save(InformazioniGenerali info) {
        // c'è una sola riga di informazioni: la aggiorno invece di crearne una nuova
        informazioniGeneraliRepository.findFirstByOrderByIdAsc()
                .ifPresent(esistente -> info.setId(esistente.getId()));

        // se lasci vuota una messa, nella lista resta un null: lo tolgo
        if (info.getOrarioMessaFeriale() != null) {
            info.getOrarioMessaFeriale().removeIf(Objects::isNull);
        }
        if (info.getOrarioMessaFestivo() != null) {
            info.getOrarioMessaFestivo().removeIf(Objects::isNull);
        }
        return informazioniGeneraliRepository.save(info);
    }
}