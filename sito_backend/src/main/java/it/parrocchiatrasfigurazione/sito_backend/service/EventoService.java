package it.parrocchiatrasfigurazione.sito_backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.parrocchiatrasfigurazione.sito_backend.exception.DuplicateEventoException;
import it.parrocchiatrasfigurazione.sito_backend.model.Evento;
import it.parrocchiatrasfigurazione.sito_backend.repository.EventoRepository;

@Service
public class EventoService {

    private EventoRepository eventoRepository;
    private FileStorageService fileStorageService;

    public EventoService(EventoRepository eventoRepository, FileStorageService fileStorageService) {
        this.eventoRepository = eventoRepository;
        this.fileStorageService = fileStorageService;
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
        String titolo = evento.getTitolo();
        java.time.LocalDate data = evento.getData();
        if (evento.getId() == null) {
            if (eventoRepository.existsByTitoloAndData(titolo, data))
                throw new DuplicateEventoException(titolo, data);
        } else {
            if (eventoRepository.existsByTitoloAndDataAndIdNot(titolo, data, evento.getId()))
                throw new DuplicateEventoException(titolo, data);
        }
        return eventoRepository.save(evento);
    }

    // Per le foto

    @Transactional
    public Evento save(Evento evento, MultipartFile file) {
        if (evento.getId() != null) {
            // in modifica, l'immagine attuale la prendo dal DB e non dal form
            evento.setImmagine(eventoRepository.findById(evento.getId()).get().getImmagine());
        }
        if (file != null && !file.isEmpty()) {
            String vecchia = evento.getImmagine();
            evento.setImmagine(fileStorageService.salva(file));
            fileStorageService.elimina(vecchia);
        }
        return this.save(evento); // il save(Evento) che hai già, con il controllo duplicati
    }

    @Transactional
    public void nascondi(Long id) {
        Evento evento = getEvento(id);
        evento.setVisibilità(false);
        eventoRepository.save(evento);
    }

    @Transactional
    public void delete(Long id) {
        eventoRepository.deleteById(id);
        fileStorageService.elimina(getEvento(id).getImmagine());
    }

}