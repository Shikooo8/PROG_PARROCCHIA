package it.parrocchiatrasfigurazione.sito_backend.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.parrocchiatrasfigurazione.sito_backend.model.Credenziali;

public interface CredenzialiRepository extends CrudRepository<Credenziali, Long>{

    Boolean existsByUsername(String username);

    Optional<Credenziali> findByUsername(String username);


}
