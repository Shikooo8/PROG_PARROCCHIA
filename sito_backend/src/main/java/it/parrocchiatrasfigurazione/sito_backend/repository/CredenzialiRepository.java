package it.parrocchiatrasfigurazione.sito_backend.repository;
import org.springframework.data.repository.CrudRepository;

import it.parrocchiatrasfigurazione.sito_backend.model.Credenziali;

public interface CredenzialiRepository extends CrudRepository<Credenziali, Long>{

    Boolean existsByUsername(String username);

}
