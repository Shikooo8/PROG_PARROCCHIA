package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.parrocchiatrasfigurazione.sito_backend.model.InformazioniGenerali;

public interface InformazioniGeneraliRepository extends CrudRepository<InformazioniGenerali, Long> {
    // ci aspettiamo una sola riga in tabella: prendiamo la prima disponibile
    Optional<InformazioniGenerali> findFirstByOrderByIdAsc();
}