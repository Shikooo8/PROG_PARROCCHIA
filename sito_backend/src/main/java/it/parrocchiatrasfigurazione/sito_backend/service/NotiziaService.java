package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Notizia;
import it.parrocchiatrasfigurazione.sito_backend.repository.NotiziaRepository;

@Service
public class NotiziaService {

    private final NotiziaRepository notiziaRepository;

    public NotiziaService(NotiziaRepository notiziaRepository) {
        this.notiziaRepository = notiziaRepository;
    }

    @Transactional(readOnly = true)
    public List<Notizia> getTutteLeNotizie() {
        return notiziaRepository.findAllByOrderByDataDesc();
    }

    @Transactional(readOnly = true)
    public List<Notizia> getUltimeNotizie() { //TODO usare una query
        return notiziaRepository.findAllByOrderByDataDesc().stream().limit(3).toList();
    }

    @Transactional
    public Notizia save(Notizia notizia) {
        return notiziaRepository.save(notizia);
    }

    @Transactional
    public void elimina(Long id) {
        notiziaRepository.deleteById(id);
    }
} 
