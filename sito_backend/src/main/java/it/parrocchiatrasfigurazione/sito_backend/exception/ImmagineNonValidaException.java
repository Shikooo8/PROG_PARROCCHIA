package it.parrocchiatrasfigurazione.sito_backend.exception;

public class ImmagineNonValidaException extends RuntimeException {
    public ImmagineNonValidaException() {
        super("Il file caricato non è un'immagine valida");
    }
}