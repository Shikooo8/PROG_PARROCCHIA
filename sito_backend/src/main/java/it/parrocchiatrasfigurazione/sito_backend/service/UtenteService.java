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

    public UtenteService(UtenteRepository utenteRepository, CredenzialiService credenzialiService, PasswordEncoder passwordEncoder, IniziativaRepository iniziativaRepository) {
        this.utenteRepository = utenteRepository;
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
        utenteRepository.rimuoviIscrizioni(id); 
        iniziativaRepository.rimuoviCoordinatore(id);
        utenteRepository.deleteById(id);
    }

    public Utente getUtenteById(Long utenteId) {
       return utenteRepository.findById(utenteId).get();
    }
}     
