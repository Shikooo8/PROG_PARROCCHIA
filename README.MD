# Sistema WEB per la Parrocchia della Trasfigurazione

Progetto per dare alla parrocchia un proprio sito

## Descrizione Progetto e finalità

Un sistema per la gestione degli eventi in parrocchia, utilizzato come progetto personale dell'esame di Sistemi Informativi sul Web dell'anno accademico 2025/2026


### Funzionalità pubbliche 


### Funzionalità utenti registrati (assistenti)

### Funzionalità riservate all’amministratore (preti)





## Errori trovati



# Descrizione del Progetto Personale 2026

Progetto Personale 2026 
Finalità per ora:
Deve essere un sistema per la gestione del sito di una parrocchia, che mostra le informazioni principali, notizie, eventi, iniziative, con un login di utenti registrati e degli admin, ma sia anche in parte visibile a chi non ha login.
L'admin può creare, modificare e rimuovere iniziative, se vuole assegnandogli un utente come coordinatore, eventi e notizie. Per eventi ed iniziative può scegliere se renderle visibili o meno. 
Le iniziative raccolgono l'elenco degli utenti registrati iscritti, e questo elenco è visibile l'utente coordinatore e dagli admin, entrambi i ruoli le possono modificare.
L'admin può modificare le informazioni principali, come l'orario delle messe e altro, e aggiungere e cancellare le notizie. 
Dividiamo Utente e Credenziali.
Solo l'admin può registrare nuovi utenti, mentre tutti possono fare login (solo se sono già nel sistema). Utenti non registrati, vedendo il "Login" e non potendo ancora loggarsi possono creare delle richieste di registrazione con i loro dati che possono essere viste soltanto dagli admin in una lista, al momento non ci interessa della privacy di quei dati, anche perché per ora sarà l'admin a mano che dovrà registrarli. Infatti accanto alla lista delle richieste (con accanto ad ogni elemento della lista il tasto cancella, per eliminarle, anche dal database) faremo il form per registrare gli utenti, tutto visibile solo all'admin, che quando inserisce i campi può cliccare salva e ci sarà un nuovo utente registrato. L'admin può vedere tutti gli utenti registrati, vedendone solo il nome, cognome e data di nascita.

Grafica: tutta la grafica deve essere gestita unicamente in file .css. Ci sta un riquadro fisso in alto con il nome della parrocchia, con login (se non sono loggato) o logout (se sono loggato) questo è riquadroAltissimo.html. Teniamoci in una palette di colori:
:root {
    --marrone: #4A3018;        
    --legno: #8B5A2B;          
    --avana: #D2B48C;          
    --beige: #F5F5DC;          
    --bianco: #FFFFFF;         
    --celestino: #B3DDF2;      

    /* Colori testo e accessori */
    --testo-principale: #332211;
    --testo-sfumato: #6b4d31;



Bisogna gestire le immagini proprie del sito e se si voglino aggiungere immagini alle entità (Eventi e Iniziative) e magari opzionalmente agli utenti.



Implementato ora:
sito_backend/src/main/java/it/parrocchiatrasfigurazione/sito_backend :
-/authentication:
--SecurityConfiguration
-/controller:
--AdminUtentiController: @GetMapping("/admin/richieste"), @GetMapping("/admin/richieste/{id}/elimina"), @GetMapping("/admin/utenti")
--AuthController:     @GetMapping("/login") return "login"
--EventoController:     @GetMapping("/eventi") return "eventi/list";     @GetMapping("/eventi/{id}") return "eventi/show";
--HomeController: @GetMapping("/") return "index"
--IniziativaController:     @GetMapping("/iniziative") return "iniziative/list";         @GetMapping("/iniziative/{id}") return "iniziative/show";
--NotiziaController:     @GetMapping("/notizie")  return "notizie/list";     @GetMapping("/notizia/{id}") return "notizie/show";
--UtenteRichiestaController:     @GetMapping("/richiesta-registrazione") return "richiesta-registrazione/form",  @PostMapping("/richiesta-registrazione") return "redirect:/richiesta-registrazione?inviata";

-/model:
--Credenziali: Long id, username, password, ruolo, OnetoOne utente
--Evento:Long id, boolean visibilità, titolo, java.time.LocalDate data, descrizione
--InformazioniGenerali:Long id, indirizzo,  java.time.LocalTime orarioApertura, java.time.LocalTime orarioChiusura, private List<java.time.LocalTime> orarioMessaFeriale,
 List<java.time.LocalTime> orarioMessaFestivo
--Iniziativa:Long id, boolean visibilità, titolo, descrizione,@ManyToMany List<Utente> iscrizioni, @ManyToOne Utente coordinatore
--Notizia:Long id, titolo, descrizione, java.time.LocalDateTime data
--Utente:Long id, nome, cognome,java.time.LocalDate dataNascita, email, @ManyToMany(mappedBy="iscrizioni") list<Iniziativa> iniziative, @OneToMany(mappedBy="coordinatore") List<Iniziativa> iniziativeCoordinate
--UtenteRichiesta:Long id, nome, cognome, motivazioni, username, password

-/repository:
(estendono CrudRepository<il loro modello, Long>)
--CredenzialiRepository :     Boolean existsByUsername(String username);
--EventoRepository:List<Evento> findByVisibilitaTrueOrderByDataAsc(), List<Evento> findFirst3ByVisibilitaTrueAndDataGreaterThanEqualOrderByDataAsc(LocalDate data);
--InformazioniGeneraliRepository: Optional<InformazioniGenerali> findFirstByOrderByIdAsc();
--IniziativaRepository : List<Iniziativa> findByVisibilitaTrue();     List<Iniziativa> findFirst3ByVisibilitàTrueOrderByIdDesc();
--NotiziaRepository:     List<Notizia> findAllByOrderByDataDesc();
--UtenteRepository:     List<Utente> findByEmail(String email), List<Utente> findAll()
--UtenteRichiestaRepository:List<UtenteRichiesta> findAllByOrderByIdDesc();

-/service
--CredenzialiService: boolean existsByUsername(String username),
--EventoService: List<Evento> getEventiVisibili(), List<Evento> getEventiVisibiliProssimi(), Evento getEvento(Long id), Evento save(Evento evento), void nascondi(Long id),  void elimina(Long id)
--InformazioniGeneraliService: InformazioniGenerali getInformazioni(), InformazioniGenerali save(InformazioniGenerali info)
--IniziativaService: List<Iniziativa> getIniziativeVisibili(), List<Iniziativa> getIniziativeVisibiliProssime()
--NotiziaService: List<Notizia> getTutteLeNotizie(), List<Notizia> getUltimeNotizie(), Notizia save(Notizia notizia), void elimina(Long id)
--UtenteRichiestaService:    List<UtenteRichiesta> getTutteLeRichieste(), UtenteRichiesta save(UtenteRichiesta richiesta),  void elimina(Long id)
--UtenteService: List<Utente> getTuttiGliUtenti(), Utente registraUtente(Utente utente, String username, String passwordInChiaro)







/sito_backend/src/main/resources:

-/static:
--/css:
---stile.css
--/images:
---cross.png (icona da vedere sulla scheda)
---Transifiguration_Raphael.jpg

-/templates:

--/admin: (qui metteremo le pagine visibile solo agli amministratori):
---richieste.html (da fare)

--/eventi:
---list.html
---show.html

--/fragments:
---riquadroAltissimo.html 

--/iniziative:
---list.html
---show.html

--/notizie:
---list.html
---show.html

--index.html

-application.properties

-import.sql 


