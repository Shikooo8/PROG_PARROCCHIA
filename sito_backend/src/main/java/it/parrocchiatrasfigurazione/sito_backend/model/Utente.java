package it.parrocchiatrasfigurazione.sito_backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Utente {

       @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String cognome;
    private java.time.LocalDate dataNascita;
    private String email;


    @ManyToMany (mappedBy = "iscrizioni")
    private List<Iniziativa> iniziative; //un utente si può registrare a tante iniziative

    @OneToMany (mappedBy = "coordinatore")
    private List<Iniziativa> iniziativeCoordinate; //un utente può gestire tante iniziative


    //====================================== GETTERS & SETTERS ==============================

    
    public List<Iniziativa> getIniziative() {
        return iniziative;
    }
    public void setIniziative(List<Iniziativa> iniziative) {
        this.iniziative = iniziative;
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
    
    public List<Iniziativa> getIniziativeCoordinate() {
        return iniziativeCoordinate;
    }
    public void setIniziativeCoordinate(List<Iniziativa> iniziativeCoordinate) {
        this.iniziativeCoordinate = iniziativeCoordinate;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

     public java.time.LocalDate getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(java.time.LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

}
