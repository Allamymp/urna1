package org.ifpe.dws3.prova1.service;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.ifpe.dws3.prova1.entity.Candidate;
import org.ifpe.dws3.prova1.entity.PoliticalParty;
import org.ifpe.dws3.prova1.repository.CandidateRepository;
import org.ifpe.dws3.prova1.repository.PoliticalPartyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Singleton
public class DataInitializerService {

    @Inject
    private  CandidateRepository candidateRepository;
    @Inject
    private PoliticalPartyRepository politicalPartyRepository;


    private Random random = new Random();


    @PostConstruct
    public void initializeData() {

        System.out.println("INICIANDO A INSERCAO DE DADOS NESSE CARALHO...");
        // Criando 3 partidos
        PoliticalParty party1 = new PoliticalParty(null, "Partido A", 1, new ArrayList<>(), 0, "PA");
        PoliticalParty party2 = new PoliticalParty(null, "Partido B", 2, new ArrayList<>(), 0, "PB");
        PoliticalParty party3 = new PoliticalParty(null, "Partido C", 3, new ArrayList<>(), 0, "PC");

        politicalPartyRepository.save(party1);
        politicalPartyRepository.save(party2);
        politicalPartyRepository.save(party3);

        List<Candidate> candidates = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Candidate candidate = createCandidate("Candidato " + (i + 1) + " do Partido A", 100 + i, party1);
            candidates.add(candidate);
        }

        for (int i = 0; i < 5; i++) {
            Candidate candidate = createCandidate("Candidato " + (i + 1) + " do Partido B", 200 + i, party2);
            candidates.add(candidate);
        }

        for (int i = 0; i < 5; i++) {
            Candidate candidate = createCandidate("Candidato " + (i + 1) + " do Partido C", 300 + i, party3);
            candidates.add(candidate);
        }

        for (Candidate candidate : candidates) {
            int votes = random.nextInt(20) + 1;
            candidate.setVote(votes);
            candidateRepository.save(candidate);
        }

        System.out.println(" TERMINOU ESSE CARALHO...");
    }

    private Candidate createCandidate(String name, Integer number, PoliticalParty party) {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setNumber(number);
        candidate.setParty(party);
        return candidateRepository.save(candidate);
    }
}
