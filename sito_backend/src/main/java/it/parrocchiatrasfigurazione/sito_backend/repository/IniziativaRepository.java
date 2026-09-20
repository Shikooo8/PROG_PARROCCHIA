package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.parrocchiatrasfigurazione.sito_backend.model.Iniziativa;
import it.parrocchiatrasfigurazione.sito_backend.model.Utente;

public interface IniziativaRepository extends CrudRepository<Iniziativa, Long> {

    List<Iniziativa> findByVisibilitàTrue();

    // TODO non ha senso ordinarli per id
    List<Iniziativa> findFirst3ByVisibilitàTrueOrderByIdDesc();

    List<Iniziativa> findAllByOrderByIdDesc();

    @Modifying
    @Query("UPDATE Iniziativa i SET i.coordinatore = null WHERE i.coordinatore.id = :id")
    void rimuoviCoordinatore(@Param("id") Long id );
}
