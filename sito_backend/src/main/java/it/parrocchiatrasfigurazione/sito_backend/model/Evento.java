package it.parrocchiatrasfigurazione.sito_backend.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Evento {
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank 
    private String titolo;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private java.time.LocalDate data;
    
    private String descrizione;

    private boolean visibilità;

    
    //====================================== GETTERS & SETTERS ==============================
    
    
    
    public boolean isVisibilità() {
        return visibilità;
    }

    public void setVisibilità(boolean visibilità) {
        this.visibilità = visibilità;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public java.time.LocalDate getData() {
        return data;
    }

    public void setData(java.time.LocalDate data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }





}
