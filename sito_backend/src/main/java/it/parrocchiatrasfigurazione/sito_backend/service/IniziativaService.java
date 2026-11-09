package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.parrocchiatrasfigurazione.sito_backend.exception.DuplicateIniziativaException;
import it.parrocchiatrasfigurazione.sito_backend.model.Iniziativa;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;
import it.parrocchiatrasfigurazione.sito_backend.repository.IniziativaRepository;

@Service
public class IniziativaService {
    private IniziativaRepository iniziativaRepository;
    private FileStorageService fileStorageService;

    public IniziativaService(IniziativaRepository iniziativaRepository, FileStorageService fileStorageService) {
        this.iniziativaRepository = iniziativaRepository;
        this.fileStorageService = fileStorageService;

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
    private Iniziativa save(Iniziativa iniziativa) {
        return iniziativaRepository.save(iniziativa);
    }

    @Transactional
    public Iniziativa save(Iniziativa form, MultipartFile file) {
        controllaDuplicati(form);

        Iniziativa iniziativa;
        if (form.getId() == null) {
            iniziativa = form; 
        } else {
            iniziativa = iniziativaRepository.findById(form.getId()).get();
            iniziativa.setTitolo(form.getTitolo()); // copio solo i campi del form
            iniziativa.setDescrizione(form.getDescrizione());
            iniziativa.setVisibilità(form.isVisibilità());
            iniziativa.setCoordinatore(form.getCoordinatore());
        }

        String vecchia = iniziativa.getImmagine();
        boolean nuovaImmagine = file != null && !file.isEmpty();
        if (nuovaImmagine) {
            iniziativa.setImmagine(fileStorageService.salva(file));
        }

        Iniziativa salvata = iniziativaRepository.save(iniziativa);

        if (nuovaImmagine && vecchia != null) {
            fileStorageService.elimina(vecchia); // solo dopo il salvataggio
        }
        return salvata;
    }

    private void controllaDuplicati(Iniziativa i) {
        boolean esiste = (i.getId() == null)
                ? iniziativaRepository.existsByTitolo(i.getTitolo())
                : iniziativaRepository.existsByTitoloAndIdNot(i.getTitolo(), i.getId());
        if (esiste)
            throw new DuplicateIniziativaException(i.getTitolo());
    }

    @Transactional
    public void delete(Long id) {
        fileStorageService.elimina(getIniziativa(id).getImmagine());
        iniziativaRepository.deleteById(id);
    }

    public void addUtenteIscritto(Long iniziativaId, Utente utente) {
        Iniziativa iniziativa = iniziativaRepository.findById(iniziativaId)
                .orElseThrow(() -> new RuntimeException("Iniziativa non trovata"));

        if (!iniziativa.getIscrizioni().contains(utente)) {
            iniziativa.getIscrizioni().add(utente);
            iniziativaRepository.save(iniziativa);
        }
    }

    @Transactional
    public void removeUtenteIscritto(Long iniziativaId, Utente utente) {
        Iniziativa iniziativa = iniziativaRepository.findById(iniziativaId)
                .orElseThrow(() -> new RuntimeException("Iniziativa non trovata"));

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
