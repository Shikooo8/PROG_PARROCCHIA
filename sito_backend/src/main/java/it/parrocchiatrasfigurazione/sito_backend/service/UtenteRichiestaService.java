package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.UtenteRichiesta;
import it.parrocchiatrasfigurazione.sito_backend.repository.UtenteRichiestaRepository;

@Service
public class UtenteRichiestaService {

    private final UtenteRichiestaRepository utenteRichiestaRepository;

    public UtenteRichiestaService(UtenteRichiestaRepository utenteRichiestaRepository) {
        this.utenteRichiestaRepository = utenteRichiestaRepository;
    }

    @Transactional(readOnly = true)
    public List<UtenteRichiesta> getTutteLeRichieste() {
        return utenteRichiestaRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public UtenteRichiesta save(UtenteRichiesta richiesta) {
        return utenteRichiestaRepository.save(richiesta);
    }

    @Transactional
    public void elimina(Long id) {
        utenteRichiestaRepository.deleteById(id);
    }
}