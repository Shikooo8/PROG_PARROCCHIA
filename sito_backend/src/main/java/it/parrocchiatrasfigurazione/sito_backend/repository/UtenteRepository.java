package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    List<Utente> findByEmail(String email);
    List<Utente> findAll();
    List<Utente> findAllByOrderByCognomeAscNomeAsc();
}