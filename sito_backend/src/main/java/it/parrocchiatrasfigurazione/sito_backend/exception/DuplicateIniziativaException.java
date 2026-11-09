package it.parrocchiatrasfigurazione.sito_backend.exception;


public class DuplicateIniziativaException extends RuntimeException{
        public DuplicateIniziativaException(String titolo) {
        super("L'iniziativa : '" + titolo + " è già presente nel sistema");
    }
}
