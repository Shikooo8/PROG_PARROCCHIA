package it.parrocchiatrasfigurazione.sito_backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity 
public class InformazioniGenerali {

   @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String indirizzo;

    private java.time.LocalTime orarioApertura;
    private java.time.LocalTime orarioChiusura;
    private List<java.time.LocalTime> orarioMessaFeriale;
    private List<java.time.LocalTime> orarioMessaFestivo;



    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public java.time.LocalTime getOrarioApertura() {
        return orarioApertura;
    }
    public void setOrarioApertura(java.time.LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }
    public java.time.LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }
    public void setOrarioChiusura(java.time.LocalTime orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }
    public List<java.time.LocalTime> getOrarioMessaFeriale() {
        return orarioMessaFeriale;
    }
    public void setOrarioMessaFeriale(List<java.time.LocalTime> orarioMessaFeriale) {
        this.orarioMessaFeriale = orarioMessaFeriale;
    }
    public List<java.time.LocalTime> getOrarioMessaFestivo() {
        return orarioMessaFestivo;
    }
    public void setOrarioMessaFestivo(List<java.time.LocalTime> orarioMessaFestivo) {
        this.orarioMessaFestivo = orarioMessaFestivo;
    }

       public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    


}
