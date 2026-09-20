package it.parrocchiatrasfigurazione.sito_backend.exception;


public class DuplicateEventoException extends RuntimeException{
        public DuplicateEventoException(String titolo, java.time.LocalDate data) {
        super("L'evento '" + titolo + "' (" + data + ") è già presente nel sistema");
    }
}
