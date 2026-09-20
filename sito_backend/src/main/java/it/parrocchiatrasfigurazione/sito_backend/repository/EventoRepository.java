package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.parrocchiatrasfigurazione.sito_backend.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {
    List<Evento> findByVisibilitàTrueOrderByDataAsc();

    List<Evento> findFirst3ByVisibilitàTrueAndDataGreaterThanEqualOrderByDataAsc(LocalDate data);

    List<Evento> findAllByOrderByDataAsc();

    Boolean existsByTitoloAndData(String titolo, LocalDate data);
    Boolean existsByTitoloAndDataAndIdNot(String titolo, LocalDate data, Long id);

    

}
