package it.parrocchiatrasfigurazione.sito_backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity 
public class UtenteRichiesta {
      @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank 
    private String nome;
    @NotBlank 
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String motivazioni;
    @NotBlank 
    private String username;
    @NotBlank 
    private String password;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getMotivazioni() {
        return motivazioni;
    }
    public void setMotivazioni(String motivazioni) {
        this.motivazioni = motivazioni;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
    

    

}
