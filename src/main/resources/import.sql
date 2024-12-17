-- Inserindo dados de PoliticalParty
INSERT INTO political_party (id, name, number, votes, abbreviation) VALUES (1, 'Partido A', 1, 0, 'PA');
INSERT INTO political_party (id, name, number, votes, abbreviation) VALUES (2, 'Partido B', 2, 0, 'PB');
INSERT INTO political_party (id, name, number, votes, abbreviation) VALUES (3, 'Partido C', 3, 0, 'PC');

-- Inserindo dados de Candidate
INSERT INTO candidate (id, name, number, votes, party_id) VALUES (1, 'Candidato 1 do Partido A', 100, 0, 1);
INSERT INTO candidate (id, name, number, votes, party_id) VALUES (2, 'Candidato 2 do Partido A', 101, 0, 1);
INSERT INTO candidate (id, name, number, votes, party_id) VALUES (3, 'Candidato 1 do Partido B', 200, 0, 2);
INSERT INTO candidate (id, name, number, votes, party_id) VALUES (4, 'Candidato 2 do Partido B', 201, 0, 2);
INSERT INTO candidate (id, name, number, votes, party_id) VALUES (5, 'Candidato 1 do Partido C', 300, 0, 3);
