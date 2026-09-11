package it.parrocchiatrasfigurazione.sito_backend.model;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Iniziativa {
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank 
    private String titolo;


    private String descrizione;

    private boolean visibilità;

    @ManyToMany
    private List<Utente> iscrizioni; //una iniziative può avere tanti iscritti


    @ManyToOne 
    private Utente coordinatore; //una iniziativa ha un coordinatore
    
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Utente> getIscrizioni() {
        return iscrizioni;
    }

    public void setIscrizioni(List<Utente> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    public Utente getCoordinatore() {
        return coordinatore;
    }

    public void setCoordinatore(Utente coordinatore) {
        this.coordinatore = coordinatore;
    }
    


    


}
