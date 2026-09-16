package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Credenziali;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.repository.UtenteRepository;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final CredenzialiService credenzialiService;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, CredenzialiService credenzialiService, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.credenzialiService = credenzialiService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<Utente> getTuttiGliUtenti() {
        return utenteRepository.findAll();
    }

    @Transactional
    public Utente registraUtente(Utente utente, String username, String passwordInChiaro) { //TODO password in chiaro
        if (credenzialiService.existsByUsername(username)) {
            throw new IllegalArgumentException("Username già esistente: " + username);
        }
        Credenziali credenziali = new Credenziali();
        credenziali.setUsername(username);
        credenziali.setPassword(passwordEncoder.encode(passwordInChiaro));
        credenziali.setRuolo(Credenziali.DEFAULT_ROLE);
        credenziali.setUtente(utente);
   //     utente.setCredenziali(credenziali);

        // grazie al cascade = CascadeType.ALL su Credenziali -> Utente,
        // salvare le credenziali salva anche l'utente collegato
        credenzialiService.save(credenziali);
        return utente;
    }

    public List<Utente> getTuttiDaCognome() {
        return utenteRepository.findAllByOrderByCognomeAscNomeAsc();
    }
}     
