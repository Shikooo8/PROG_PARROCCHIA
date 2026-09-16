package it.parrocchiatrasfigurazione.sito_backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.parrocchiatrasfigurazione.sito_backend.model.Evento;
import it.parrocchiatrasfigurazione.sito_backend.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Transactional(readOnly = true)
    public List<Evento> getEventiVisibili() {
        return eventoRepository.findByVisibilitàTrueOrderByDataAsc();
    }

    @Transactional(readOnly = true)
    public List<Evento> getAll() {
        return eventoRepository.findAllByOrderByDataAsc();
    }

    @Transactional(readOnly = true)
    public List<Evento> getEventiVisibiliProssimi() {
        return eventoRepository.findFirst3ByVisibilitàTrueAndDataGreaterThanEqualOrderByDataAsc(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public Evento getEvento(Long id) {
        return eventoRepository.findById(id).get();
    }

    @Transactional
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Transactional
    public void nascondi(Long id) {
        Evento evento = getEvento(id);
        evento.setVisibilità(false);
        eventoRepository.save(evento);
    }

    @Transactional
    public void elimina(Long id) {
        eventoRepository.deleteById(id);
    }
}