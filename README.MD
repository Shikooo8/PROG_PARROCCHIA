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
Deve essere un sistema per la gestione del sito di una parrocchia, che mostra le informazioni principali, notizie, eventi, iniziative, con il login di utenti registrati e gli admin  e l'accesso a chi non ha login.
L'admin può creare iniziative, se vuole assegnandogli un utente come coordinatore, ed eventi e toglierne altri, oppure fare in modo che non siano più visibili.
Le iniziative raccolgono l'elenco degli utenti registrati iscritti, e questo elenco è visibile dagli admin e l'utente coordinatore, che le possono modificare.
L'admin può modificare le informazioni principali, come l'orario delle messe e altro, e aggiungere e cancellare le notizie. 
Dividiamo Utente e Credenziali.
Solo l'admin può registrare nuovi utenti registrati, mentre tutti possono fare login (solo se sono già nel sistema). Utenti non registrati, vedendo il "Login" e non potendo ancora loggarsi possono creare delle richieste di registrazione con i loro dati che possono essere viste soltanto dagli admin in una lista, al momento non ci interessa della privacy di quei dati, anche perché per ora sarà l'admin a mano che dovrà registrarli. Infatti accanto alla lista delle richieste (con accanto ad ogni elemento della lista il tasto cancella, per eliminarle, anche dal database) faremo il form per registrare gli utenti, tutto visibile solo all'admin, che quando inserisce i campi può cliccare salva e ci sarà un nuovo utente registrato. 


Poi pensenseremo bene alla grafica. Intanto direi di lasciare un riquadro fisso in alto con il nome della parrocchia, con login (se non sono loggato) o logout (se sono loggato) questo è riquadroAltissimo.html. Tutte le cose di grafica devono essere messe in file .css, anche creando tantissime classi, così da porterle riutilizzare. Teniamoci in una palette di colori  dal marrone, legno, beige, avana, bianco e celestino pastello chiaro. 


Bisogna gestire le immagini proprie del sito e se si voglino aggiungere immagini alle entità (Eventi e Iniziative) e magari opzionalmente agli utenti.



Implementato ora:
sito_backend/src/main/java/it/parrocchiatrasfigurazione/sito_backend :
-/authentication:
--SecurityConfiguration (bozza)
-/controller:
--AdminUtentiController: @GetMapping("/admin/richieste"), @GetMapping("/admin/richieste/{id}/elimina"), @GetMapping("/admin/utenti")
--AuthController:
--EventoController:     @GetMapping("/eventi") return "eventi/list";
--HomeController: @GetMapping("/")
--IniziativaController:     @GetMapping("/iniziative") return "iniziative/list";
--NotiziaController:     @GetMapping("/notizie")  return "notizie/list";
--UtenteRichiestaController:     @GetMapping("/richiesta-registrazione") return "richiesta-registrazione/form",  @PostMapping("/richiesta-registrazione") return "redirect:/richiesta-registrazione?inviata";

-/model:
--Credenziali: Long id, username, password, ruolo, OnetoOne utente
--Evento:Long id, boolean visibilità, titolo, java.time.LocalDate data, descrizione
--InformazioniGenerali:Long id, indirizzo,  java.time.LocalTime orarioApertura, java.time.LocalTime orarioChiusura, private List<java.time.LocalTime> orarioMessaFeriale,
 List<java.time.LocalTime> orarioMessaFestivo
--Iniziativa:Long id, boolean visibilità, titolo, descrizione,@ManyToMany List<Utente> iscrizioni, @ManyToOne Utente coordinatore
--Notizia:Long id, titolo, descrizione, java.time.LocalDateTime data
--Utente:Long id, nome, cognome, email, OneToOne credenziali, @ManyToMany(mappedBy="iscrizioni") list<Iniziativa> iniziative, @OneToMany(mappedBy="coordinatore") List<Iniziativa> iniziativeCoordinate
--UtenteRichiesta:Long id, nome, cognome, motivazioni, username, password

-/repository:
(estendono CrudRepository<il loro modello, Long>)
--CredenzialiRepository :     Boolean existsByUsername(String username);
--EventoRepository:List<Evento> findByVisibilitaTrueOrderByDataAsc(), List<Evento> findByVisibilitaTrueAndDataGreaterThanEqualOrderByDataAsc(LocalDate data);
--InformazioniGeneraliRepository: Optional<InformazioniGenerali> findFirstByOrderByIdAsc();
--IniziativaRepository : List<Iniziativa> findByVisibilitaTrue();
--NotiziaRepository:     List<Notizia> findAllByOrderByDataDesc();
--UtenteRepository:     List<Utente> findByEmail(String email), List<Utente> findAll()
--UtenteRichiestaRepository:List<UtenteRichiesta> findAllByOrderByIdDesc();

-/service
--CredenzialiService: public boolean existsByUsername(String username),
--EventoService:     public List<Evento> getEventiVisibili(),     public List<Evento> getEventiVisibiliProssimi(),     public Evento getEvento(Long id),     public Evento save(Evento evento),     public void nascondi(Long id),     public void elimina(Long id)
--InformazioniGeneraliService: public InformazioniGenerali getInformazioni(),  public InformazioniGenerali save(InformazioniGenerali info)
--IniziativaService:     public List<Iniziativa> getIniziativeVisibili()
--NotiziaService:     public List<Notizia> getTutteLeNotizie(),     public List<Notizia> getUltimeNotizie(),     public Notizia save(Notizia notizia),     public void elimina(Long id)
--UtenteRichiestaService:     public List<UtenteRichiesta> getTutteLeRichieste(),     public UtenteRichiesta save(UtenteRichiesta richiesta),     public void elimina(Long id)
--UtenteService:     public List<Utente> getTuttiGliUtenti(),     public Utente registraUtente(Utente utente, String username, String passwordInChiaro)







/sito_backend/src/main/resources/templates:

-/admin (qui metteremo le pagine visibile solo agli amministratori):
--richieste.html

-/fragments:
--riquadroAltissimo.html

-index.html
