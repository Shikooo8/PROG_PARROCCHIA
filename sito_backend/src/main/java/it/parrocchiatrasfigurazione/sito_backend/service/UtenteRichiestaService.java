package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Credenziali;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.model.UtenteRichiesta;
import it.parrocchiatrasfigurazione.sito_backend.repository.UtenteRichiestaRepository;

@Service
public class UtenteRichiestaService {

    private final CredenzialiService credenzialiService;
    private final UtenteService utenteService;
    private UtenteRichiestaRepository utenteRichiestaRepository;

    public UtenteRichiestaService(UtenteRichiestaRepository utenteRichiestaRepository, UtenteService utenteService, CredenzialiService credenzialiService) {
        this.utenteRichiestaRepository = utenteRichiestaRepository;
        this.utenteService = utenteService;
        this.credenzialiService = credenzialiService;
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

    @Transactional
    public void approva(Long id) {

        UtenteRichiesta richiesta = utenteRichiestaRepository.findById(id).get();

        Utente utente = new Utente();

        utente.setNome(richiesta.getNome());
        utente.setCognome(richiesta.getCognome());
        utente.setDataNascita(richiesta.getDataNascita());
        utente.setEmail(richiesta.getEmail());

        Credenziali credenziali = new Credenziali();

        credenziali.setUsername(richiesta.getUsername());
        credenziali.setPassword(richiesta.getPassword());
        credenziali.setRuolo(Credenziali.USER_ROLE);

        credenziali.setUtente(utente);
        //utenteService.save(utente);
        credenzialiService.save(credenziali);

        utenteRichiestaRepository.deleteById(id);
    }

}