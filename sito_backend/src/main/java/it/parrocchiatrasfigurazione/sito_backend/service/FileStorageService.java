package it.parrocchiatrasfigurazione.sito_backend.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.parrocchiatrasfigurazione.sito_backend.exception.ImmagineNonValidaException;

@Service
public class FileStorageService {

    private static final List<String> TIPI_AMMESSI =
            List.of("image/jpeg", "image/png", "image/webp");

    private final Path cartella;

    public FileStorageService(@Value("${app.upload-dir}") String dir) throws IOException {
        this.cartella = Paths.get(dir).toAbsolutePath().normalize();
        Files.createDirectories(this.cartella);
    }

    /** Salva il file e ritorna il nome; ritorna null se non è stato scelto nessun file. */
    public String salva(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        if (!TIPI_AMMESSI.contains(file.getContentType())) {
            throw new ImmagineNonValidaException();
        }
        // nome leggibile: timestamp + nome originale ripulito dai caratteri strani
        String originale = file.getOriginalFilename() == null ? "img" : file.getOriginalFilename();
        String nome = System.currentTimeMillis() + "-" + originale.replaceAll("[^a-zA-Z0-9._-]", "_");
        try {
            Files.copy(file.getInputStream(), cartella.resolve(nome));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return nome;
    }

    public void elimina(String nome) {
        if (nome == null) return;
        try {
            Files.deleteIfExists(cartella.resolve(nome));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
