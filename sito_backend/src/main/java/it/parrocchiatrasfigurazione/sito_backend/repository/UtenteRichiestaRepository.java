package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.parrocchiatrasfigurazione.sito_backend.model.UtenteRichiesta;

public interface UtenteRichiestaRepository extends CrudRepository<UtenteRichiesta, Long> {
    List<UtenteRichiesta> findAllByOrderByIdDesc();

}