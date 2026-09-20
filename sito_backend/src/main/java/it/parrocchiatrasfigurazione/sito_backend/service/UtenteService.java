package it.parrocchiatrasfigurazione.sito_backend.service;

import it.parrocchiatrasfigurazione.sito_backend.repository.IniziativaRepository;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.repository.UtenteRepository;

@Service
public class UtenteService {

    private final IniziativaRepository iniziativaRepository;
    private final UtenteRepository utenteRepository;
    private final CredenzialiService credenzialiService;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, CredenzialiService credenzialiService, PasswordEncoder passwordEncoder, IniziativaRepository iniziativaRepository) {
        this.utenteRepository = utenteRepository;
        this.credenzialiService = credenzialiService;
        this.passwordEncoder = passwordEncoder;
        this.iniziativaRepository = iniziativaRepository;
    }

    @Transactional(readOnly = true)
    public List<Utente> getTuttiGliUtenti() {
        return utenteRepository.findAll();
    }

    @Transactional
    public void save(Utente utente){
        this.utenteRepository.save(utente);
    }

    public List<Utente> getTuttiDaCognome() {
        return utenteRepository.findAllByOrderByCognomeAscNomeAsc();
    }


    @Transactional
    public void delete(Long id) {
        iniziativaRepository.rimuoviCoordinatore(id);
        utenteRepository.deleteById(id);

        //TODO rimuovere le iscrizioni
    }
}     
