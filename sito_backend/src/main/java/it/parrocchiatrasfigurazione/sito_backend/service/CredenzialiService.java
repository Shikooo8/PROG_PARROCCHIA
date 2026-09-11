package it.parrocchiatrasfigurazione.sito_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Credenziali;
import it.parrocchiatrasfigurazione.sito_backend.repository.CredenzialiRepository;

@Service
public class CredenzialiService {

    private final CredenzialiRepository credenzialiRepository;

    public CredenzialiService(CredenzialiRepository credenzialiRepository) {
        this.credenzialiRepository = credenzialiRepository;
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return credenzialiRepository.existsByUsername(username);
    }

    public void save(Credenziali credenziali) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
