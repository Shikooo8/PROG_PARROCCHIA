package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.parrocchiatrasfigurazione.sito_backend.model.Notizia;

public interface NotiziaRepository extends CrudRepository<Notizia, Long> {
    List<Notizia> findAllByOrderByDataDesc();

    
}