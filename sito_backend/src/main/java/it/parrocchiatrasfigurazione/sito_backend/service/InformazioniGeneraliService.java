package it.parrocchiatrasfigurazione.sito_backend.service;

import it.parrocchiatrasfigurazione.sito_backend.model.InformazioniGenerali;
import it.parrocchiatrasfigurazione.sito_backend.repository.InformazioniGeneraliRepository;
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
        return informazioniGeneraliRepository.save(info);
    }
}