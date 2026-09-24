package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Iniziativa;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.repository.IniziativaRepository;

@Service
public class IniziativaService {
    private IniziativaRepository iniziativaRepository;

    public IniziativaService(IniziativaRepository iniziativaRepository){
        this.iniziativaRepository = iniziativaRepository;

    }

    @Transactional(readOnly = true)
    public List<Iniziativa> getIniziativeVisibili() {
        return iniziativaRepository.findByVisibilitàTrue();
    }

    public Iniziativa getIniziativa(Long id) {
        return iniziativaRepository.findById(id).get();
    }

    public List<Iniziativa> getIniziativeVisibiliProssime() {
        return iniziativaRepository.findFirst3ByVisibilitàTrueOrderByIdDesc();
    }

    @Transactional(readOnly = true)
    public List<Iniziativa> getAll() {
        return iniziativaRepository.findAllByOrderByIdDesc();
    }


    @Transactional
    public Iniziativa save(Iniziativa iniziativa) {
      /*  String titolo = iniziativa.getTitolo();
        java.time.LocalDate data = iniziativa.getData();
        if(iniziativa.getId() == null){
            if(iniziativaRepository.existsByTitoloAndData(titolo, data))
                throw new DuplicateIniziativaException(titolo, data);
        } else {
            if(iniziativaRepository.existsByTitoloAndDataAndIdNot(titolo, data, iniziativa.getId()))
                throw new DuplicateIniziativaException(titolo,data);
        }----------------------------*/
        return iniziativaRepository.save(iniziativa);
    }

       @Transactional
    public void delete(Long id) {
        iniziativaRepository.deleteById(id);
    }


    public void addUtenteIscritto(Long iniziativaId, Utente utente) {
    Iniziativa iniziativa = iniziativaRepository.findById(iniziativaId)
            .orElseThrow(() -> new RuntimeException("Iniziativa non trovata")); //TODO errrore a caso

    if (!iniziativa.getIscrizioni().contains(utente)) {
        iniziativa.getIscrizioni().add(utente);
        iniziativaRepository.save(iniziativa);
    }
}

public void removeUtenteIscritto(Long iniziativaId, Utente utente) {
    Iniziativa iniziativa = iniziativaRepository.findById(iniziativaId)
            .orElseThrow(() -> new RuntimeException("Iniziativa non trovata")); //TODO errrore a caso

    iniziativa.getIscrizioni().remove(utente);
    iniziativaRepository.save(iniziativa);
}

public boolean isUtenteIscritto(Long iniziativaId, Utente utente) {

    Iniziativa iniziativa = getIniziativa(iniziativaId);

    return iniziativa.getIscrizioni().contains(utente);
}

public boolean isCoordinatore(Long iniziativaId, Utente utente) {

    Iniziativa iniziativa = getIniziativa(iniziativaId);

    return iniziativa.getCoordinatore() != null
            && iniziativa.getCoordinatore().getId().equals(utente.getId());
}






}
