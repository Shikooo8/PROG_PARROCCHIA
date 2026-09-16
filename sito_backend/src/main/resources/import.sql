
--Utente
insert into utente (id, nome, cognome, email) values (nextval('utente_seq'), 'Anna', 'Ambrosi', 'a@a.a');
insert into utente (id, nome, cognome, email) values (nextval('utente_seq'), 'Barbara', 'Barro', 'b@a.a');
insert into utente (id, nome, cognome, email) values (nextval('utente_seq'), 'Carmine', 'Castel', 'c@a.a' );
insert into utente (id, nome, cognome, email) values (nextval('utente_seq'), 'Desiderio', 'Delpe', 'd@a.a' );
insert into utente (id, nome, cognome, email) values (nextval('utente_seq'), 'Paolo', 'Enea', 'e@a.a' );

-- Evento
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Evento1', '2026-06-10', 'ciaociaociao', '1');
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Evento2', '2026-06-10', 'ciaociaociao', '1');
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Evento3', '2026-06-10', 'ciaociaociao', '1');
insert into evento (id, titolo, data, descrizione, visibilità) values (nextval('evento_seq'), 'Evento4', '2026-06-10', 'ciaociaociao', '1');


-- InformazioniGenerale
insert into informazioni_generali (id, indirizzo, orario_apertura, orario_chiusura, orario_messa_feriale, orario_messa_festivo) values (nextval('informazioni_generali_seq'), 'Pz della Trasfigurzione, 5', '14:00', '18:00', '{8:00, 18:00}', '{9:00, 10:15, 11:30, 18:00}');

-- iniziativa
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Iniziativa1', 'ciaociaociao', '1', 1);
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Iniziativa2', 'ciaociaociao', '1', 1);
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Iniziativa3', 'ciaociaociao', '1', 1);
insert into iniziativa (id, titolo, descrizione, visibilità, coordinatore_id) values (nextval('iniziativa_seq'), 'Iniziativa4', 'ciaociaociao', '1', 1);

-- notizia
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'notizia1', 'ciaociaociao', '2026-09-10');
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'notizia2', 'ciaociaociao', '20206-09-01');
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'notizia3', 'ciaociaociao', '2026-09-13');
insert into notizia (id, titolo, descrizione, data) values (nextval('notizia_seq'), 'Notizia4', 'ciaociaociao', '2026-09-11');

-- Credenziali
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente1', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 1);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente2', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 51);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente3', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 101);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Utente4', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'USER', 151);
insert into credenziali (id, username, password, ruolo, utente_id) values (nextval('credenziali_seq'), 'Prete', '$2a$10$yWAIDyuEr78BBBFZ5cYh8.Nw4gUHFTRG5FwaWqNCGeOD8M4mh3.xy', 'ADMIN', 201);

--utente richiesta
insert into utente_richiesta (id, nome, cognome, motivazioni, username, password) values (nextval('utente_richiesta_seq'), 'Nuovo', 'Novizio', 'voglio entrare', 'NuoNO', 'Paolo');