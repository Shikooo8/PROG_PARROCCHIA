
--Utente
insert into utente (id, nome, cognome, email, data_nascita) values (nextval('utente_seq'), 'Anna', 'Ambrosi', 'anna@gmail.com', '1970-08-19');
insert into utente (id, nome, cognome, email, data_nascita) values (nextval('utente_seq'), 'Barbara', 'Barro', 'baarbare@libero.it', '2000-03-03');
insert into utente (id, nome, cognome, email, data_nascita) values (nextval('utente_seq'), 'Carmine', 'Castel', 'carmine@usa.net', '1999-06-06');
insert into utente (id, nome, cognome, email, data_nascita) values (nextval('utente_seq'), 'Desiderio', 'Delpe', 'wish@gmail.com', '2004-01-08');
insert into utente (id, nome, cognome, email, data_nascita) values (nextval('utente_seq'), 'Paolo', 'Enea', 'Paolo@gmail.com','1962-07-07' );

-- Evento
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Pellegrinaggio a Lourdes!!', '2026-10-1', '58° iniziativa spirituale al bellissimo santuario con la parrocchia', '1');
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Raccolta cibo', '2026-10-18', 'Viene Martedì a dare cibo di prima necessità per chi ne ha bisogno. Servono Legumi, oggetti per la pulizia', '1');
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Cena di insediamento', '2026-10-15', 'Occasione di festa e di ritrovo conviviale nella restaurata sala', '1');
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Festa di benvenuto!', '2026-10-10', 'Diamo il benvenuto a Francesco che verrà a stare nella nostra parrocchia, lo ringraziamo del suo impegno che sarà preziosissimo', '1');


-- InformazioniGenerale
insert into informazioni_generali (id, indirizzo, orario_apertura, orario_chiusura, orario_messa_feriale, orario_messa_festivo) values (nextval('informazioni_generali_seq'), 'Pz della Trasfigurzione, 5', '14:00', '18:00', '{8:00, 18:00}', '{9:00, 10:15, 11:30, 18:00}');

-- iniziativa
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Cresime', 'Per il Gruppo di Paola sono previste le iscrizioni entro la data riportata', '1', 1);
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Comunioni', 'Per il gruppo di Fabio bisogna sbrigarsi!', '1', 1);
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Corso pre matrimoniale', 'Il nuovo prete aprirà uno spazio per i futuri sposi della parrocchia!', '1', 1);
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Cresime Adulti', 'Per il santo sacramento della confermazione', '1', 1);

-- notizia
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'Riprende il giornale!', 'Finalmente dopo anni di assenza torna il Tabor con tutte le notizie', '2026-09-10 10:00');
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'Torna il Catechismo', 'Le iscrizioni sono aperte', '2026-09-01 08:00');
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'Preghiera per il disastro', 'Ritroviamoci per pregare insieme per questo brutto momento.', '2026-09-13 14:00');
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'Adorazione perpetua', 'Dal il giorno verrà instaurata adorazione perpetua', '2026-09-11 15:00');

-- Credenziali
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente1', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 1);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente2', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 51);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente3', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 101);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente4', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 151);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Prete', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'ADMIN', 201);

--utente richiesta
insert into utente_richiesta (id, nome, cognome, motivazioni, username, password) values (nextval('utente_richiesta_seq'), 'Nuovo', 'Novizio', 'voglio entrare', 'NuoNO', 'Paolo');