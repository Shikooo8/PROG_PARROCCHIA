package it.parrocchiatrasfigurazione.sito_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Transactional // TODO save ??
    public Iniziativa save(Iniziativa iniziativa) {
        /*
         * String titolo = iniziativa.getTitolo();
         * java.time.LocalDate data = iniziativa.getData();
         * if(iniziativa.getId() == null){
         * if(iniziativaRepository.existsByTitoloAndData(titolo, data))
         * throw new DuplicateIniziativaException(titolo, data);
         * } else {
         * if(iniziativaRepository.existsByTitoloAndDataAndIdNot(titolo, data,
         * iniziativa.getId()))
         * throw new DuplicateIniziativaException(titolo,data);
         * }----------------------------
         */
        return iniziativaRepository.save(iniziativa);
    }


    //Per le foto
    @Transactional
    public Iniziativa save(Iniziativa iniziativa, MultipartFile file) {
        if (iniziativa.getId() != null) {
            // in modifica, l'immagine attuale la prendo dal DB e non dal form
            iniziativa.setImmagine(iniziativaRepository.findById(iniziativa.getId()).get().getImmagine());
        }
        if (file != null && !file.isEmpty()) {
            String vecchia = iniziativa.getImmagine();
            iniziativa.setImmagine(fileStorageService.salva(file));
            fileStorageService.elimina(vecchia);
        }
        return this.save(iniziativa); 
    }

    @Transactional
    public void delete(Long id) {
        fileStorageService.elimina(getIniziativa(id).getImmagine());
        iniziativaRepository.deleteById(id);
    }

    public void addUtenteIscritto(Long iniziativaId, Utente utente) {
        Iniziativa iniziativa = iniziativaRepository.findById(iniziativaId)
                .orElseThrow(() -> new RuntimeException("Iniziativa non trovata")); // TODO errrore a caso

        if (!iniziativa.getIscrizioni().contains(utente)) {
            iniziativa.getIscrizioni().add(utente);
            iniziativaRepository.save(iniziativa);
        }
    }

    public void removeUtenteIscritto(Long iniziativaId, Utente utente) {
        Iniziativa iniziativa = iniziativaRepository.findById(iniziativaId)
                .orElseThrow(() -> new RuntimeException("Iniziativa non trovata")); // TODO errrore a caso

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
