# Sistema WEB per la Parrocchia della Trasfigurazione

Progetto per dare alla parrocchia un proprio sito

## Descrizione Progetto e finalità

Un sistema per la gestione degli eventi in parrocchia, utilizzato come progetto personale dell'esame di Sistemi Informativi sul Web dell'anno accademico 2025/2026


### Funzionalità pubbliche 
- Mandare la richiesta di registrazione
- Vedere Eventi, Iniziative e Notizie

### Funzionalità utenti registrati (assistenti)
- Iscriversi ad iniziative
- Coordinare le iniziative assegnate

### Funzionalità riservate all’amministratore (preti)
- Approvare le registrazioni
- Aggiungere, modifica ed eliminare eventi
- Aggiungere, modifica ed eliminare iniziative
- Aggiungere, modifica ed eliminare notizie
- gestire gli utenti


## Errori trovati
- Modificando il coordinatore, scompaiono i partecipanti 
- non posso cancellare l'escirzione ad una iniziativa dalla parte dell'admin.
- La gestione dell'iscrizione del coordinatore non ha senso

# Descrizione del Progetto Personale 2026Progetto Personale 2026 

Progetto Personale 2026 
Finalità per ora:
Deve essere un sistema per la gestione del sito di una parrocchia, che mostra le informazioni principali, notizie, eventi, iniziative, con un login di utenti registrati e degli admin, ma sia anche in parte visibile a chi non ha login.
L'admin può creare, modificare e rimuovere iniziative, se vuole assegnandogli un utente come coordinatore, eventi e notizie. Per eventi ed iniziative può scegliere se renderle visibili o meno. 
Le iniziative raccolgono l'elenco degli utenti registrati iscritti, e questo elenco è visibile l'utente coordinatore e dagli admin, entrambi i ruoli le possono modificare.
L'admin può modificare le informazioni principali, come l'orario delle messe e altro, e aggiungere e cancellare le notizie. 
Dividiamo Utente e Credenziali.
Solo l'admin può registrare nuovi utenti, mentre tutti possono fare login (solo se sono già nel sistema). Utenti non registrati, vedendo il "Login" e non potendo ancora loggarsi possono creare delle richieste di registrazione con i loro dati. L'admin vede la lista delle richieste, e può decidere se approvarle o respingerle.

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
--AdminController: @RequestMapping("/admin")  @GetMapping("/index") return "admin/index", @GetMapping("/richieste") return "admin/richieste, @PostMapping("/richieste/{id}/cancella")return "redirect:/admin/richieste"; @PostMapping("/richieste/{id}/approva")return "redirect:/admin/richieste"; @GetMapping("/eventi") return "admin/eventi/list", @GetMapping("/iniziative")return "admin/iniziative/list"; @GetMapping("/notizie")return "admin/notizie/list"; @GetMapping("/utenti")return "admin/utenti"; @PostMapping("/utenti/{id}/cancella") return "redirect:/admin/utenti";
--AuthController:@GetMapping("/login") return "login"
--EventoController:     @GetMapping("/eventi") return "eventi/list"; @GetMapping("/eventi/{id}") return "eventi/show"; @GetMapping("/admin/eventi/nuovo") return "admin/eventi/nuovo";@PostMapping("/admin/eventi/nuovo") return "redirect:/admin/eventi"; @GetMapping("/admin/eventi/{id}/modifica") return "admin/eventi/modifica"; @PostMapping("/admin/eventi/{id}/modifica") return "redirect:/admin/eventi" ; @PostMapping("/admin/eventi/{id}/cancella")   return "redirect:/admin/eventi";
--GlobalController:     @ModelAttribute("userDetails")
--HomeController: @GetMapping("/") return "index"
--IniziativaController: @GetMapping("/iniziative") return "iniziative/list"; @GetMapping("/iniziative/{id}") return "iniziative/show"; @GetMapping("/admin/iniziative/nuovo")return "admin/iniziative/form"; @PostMapping("/admin/iniziative/nuovo")return "redirect:/admin/iniziative"; @GetMapping("/admin/iniziative/{id}/modifica")return "admin/iniziative/form"; @PostMapping("/admin/iniziative/{id}/modifica")return "redirect:/admin/iniziative";  @PostMapping("/admin/iniziative/{id}/cancella") return "redirect:/admin/iniziative";
--NotiziaController:     @GetMapping("/notizie")  return "notizie/list";     @GetMapping("/notizia/{id}") return "notizie/show"; @GetMapping("/admin/notizie/nuovo")return "admin/notizie/form";     @PostMapping("/admin/notizie/nuovo")return "redirect:/admin/notizie";     @GetMapping("/admin/notizie/{id}/modifica")return "admin/notizie/form";     @PostMapping("/admin/notizie/{id}/modifica")return "redirect:/admin/notizie";     @PostMapping("/admin/notizie/{id}/cancella")return "redirect:/admin/notizie";
--UtenteRichiestaController:     @GetMapping("/richiesta-registrazione") return "richiesta",  @PostMapping("/richiesta-registrazione") return "redirect:/richiesta-registrazione?inviata";
-/exception
--DuplicateEventoException : titolo&data
-/model:
--Credenziali: Long id, username, password, ruolo, OnetoOne utente
--Evento:Long id, boolean visibilità, titolo, java.time.LocalDate data, descrizione
--InformazioniGenerali:Long id, indirizzo,  java.time.LocalTime orarioApertura, java.time.LocalTime orarioChiusura, private List<java.time.LocalTime> orarioMessaFeriale,
 List<java.time.LocalTime> orarioMessaFestivo
--Iniziativa:Long id, boolean visibilità, titolo, descrizione,@ManyToMany List<Utente> iscrizioni, @ManyToOne Utente coordinatore
--Notizia:Long id, titolo, descrizione, java.time.LocalDateTime data
--Utente:Long id, nome, cognome,java.time.LocalDate dataNascita, email, @ManyToMany(mappedBy="iscrizioni") list<Iniziativa> iniziative, @OneToMany(mappedBy="coordinatore") List<Iniziativa> iniziativeCoordinate
--UtenteRichiesta:Long id, nome, cognome, email, dataNascita motivazioni, username, password
-/repository:
(estendono CrudRepository<il loro modello, Long>)
--CredenzialiRepository :     Boolean existsByUsername(String username);
--EventoRepository:List<Evento> findByVisibilitaTrueOrderByDataAsc(), List<Evento> findFirst3ByVisibilitaTrueAndDataGreaterThanEqualOrderByDataAsc(LocalDate data); List<Evento> findAllByOrderByDataAsc(); Boolean existsByTitoloAndData(String titolo, LocalDate data); Boolean existsByTitoloAndDataAndIdNot(String titolo, LocalDate data, Long id);
--InformazioniGeneraliRepository: Optional<InformazioniGenerali> findFirstByOrderByIdAsc();
--IniziativaRepository : List<Iniziativa> findByVisibilitaTrue(); List<Iniziativa> findFirst3ByVisibilitàTrueOrderByIdDesc(); List<Iniziativa> findAllByOrderByIdDesc();@Modifying @Query("UPDATE Iniziativa i SET i.coordinatore = null WHERE i.coordinatore.id = :id") void rimuoviCoordinatore(@Param("id") Long id );
--NotiziaRepository:     List<Notizia> findAllByOrderByDataDesc();
--UtenteRepository: List<Utente> findByEmail(String email), List<Utente> findAll(), List<Utente> findAllByOrderByCognomeAscNomeAsc();
--UtenteRichiestaRepository:List<UtenteRichiesta> findAllByOrderByIdDesc(); 

-/service
--CredenzialiService: boolean existsByUsername(String username), void save(Credenziali credenziali)
--EventoService: List<Evento> getEventiVisibili(), List<Evento> getAll(),  List<Evento> getEventiVisibiliProssimi(), Evento getEvento(Long id), Evento save(Evento evento), void nascondi(Long id),  void delete(Long id)
--InformazioniGeneraliService: InformazioniGenerali getInformazioni(), InformazioniGenerali save(InformazioniGenerali info)
--IniziativaService: List<Iniziativa> getIniziativeVisibili(), List<Iniziativa> getIniziativeVisibiliProssime(), List<Iniziativa> getAll(); Iniziativa save(Iniziativa iniziativa); void delete(Long id);
--NotiziaService: List<Notizia> getTutteLeNotizie(), List<Notizia> getUltimeNotizie(), Notizia save(Notizia notizia), void delete(Long id)
--UtenteRichiestaService:    List<UtenteRichiesta> getTutteLeRichieste(), UtenteRichiesta save(UtenteRichiesta richiesta),  void elimina(Long id), void approva(Long id)
--UtenteService: List<Utente> getTuttiGliUtenti(), Utente registraUtente(Utente utente, String username, String passwordInChiaro), List<Utente> getTuttiDaCognome(), void delete(Loing id)



/sito_backend/src/main/resources:

-/messages/messages.properties= evento.duplicate

-/static:
--/css:
---stile.css
--/images:
---cross.png (icona da vedere sulla scheda)
---Transifiguration_Raphael.jpg

-/templates:

--/admin:
---/eventi
----list.html
----form.html
---/iniziative:
----form.html
----list.html
---/notizie:
----form.html
----list.html
---index.html
---richieste.html 
---utenti.html

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
--login.html
--richiesta.html

-application.properties

-import.sql 


