package it.parrocchiatrasfigurazione.sito_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.parrocchiatrasfigurazione.sito_backend.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    List<Utente> findByEmail(String email);

    List<Utente> findAll();

    List<Utente> findAllByOrderByCognomeAscNomeAsc();

    @Modifying
    @Query(value = "DELETE FROM iniziativa_iscrizioni WHERE iscrizioni_id = :id", nativeQuery = true)
    void rimuoviIscrizioni(@Param("id") Long id);
}