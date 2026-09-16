package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Iniziativa;
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

}
