package it.parrocchiatrasfigurazione.sito_backend.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.parrocchiatrasfigurazione.sito_backend.model.Iniziativa;

public interface IniziativaRepository extends CrudRepository<Iniziativa, Long>{

    List<Iniziativa> findByVisibilitàTrue();

}
