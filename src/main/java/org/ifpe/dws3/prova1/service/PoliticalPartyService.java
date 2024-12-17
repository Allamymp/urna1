package org.ifpe.dws3.prova1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ifpe.dws3.prova1.entity.Candidate;
import org.ifpe.dws3.prova1.entity.PoliticalParty;
import org.ifpe.dws3.prova1.repository.PoliticalPartyRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PoliticalPartyService {

    @Inject
    private PoliticalPartyRepository politicalPartyRepository;
    @Inject
    private CandidateService candidateService;


    public PoliticalParty save(PoliticalParty politicalParty) {

        return politicalPartyRepository.save(politicalParty);
    }


    public List<PoliticalParty> findAll() {

        List<PoliticalParty> parties = politicalPartyRepository.findAll();

        return parties;
    }


    public Optional<PoliticalParty> findById(Long id) {

        return politicalPartyRepository.findById(id);
    }


    public Optional<PoliticalParty> findByName(String name) {

        return politicalPartyRepository.findByName(name);
    }

    public Optional<PoliticalParty> findByNumber(Integer number) {

        return politicalPartyRepository.findByNumber(number);
    }


    public Optional<PoliticalParty> findByAbbreviation(String abbreviation) {

        return politicalPartyRepository.findByAbbreviation(abbreviation);
    }


    public void addCandidate(Long partyId, Long candidateId) {


        // Busca pelo partido e candidato
        Optional<PoliticalParty> politicalPartyOpt = findById(partyId);
        Optional<Candidate> candidateOpt = candidateService.findById(candidateId);

        if (politicalPartyOpt.isPresent()) {
            PoliticalParty politicalParty = politicalPartyOpt.get();
            if (candidateOpt.isPresent()) {
                Candidate candidate = candidateOpt.get();

                boolean candidateExists = politicalParty.getCandidates().stream()
                        .anyMatch(existingCandidate -> existingCandidate.getNumber().equals(candidate.getNumber()));

                if (candidateExists) {
                    throw new IllegalArgumentException("Candidate with number " + candidate.getNumber() + " already exists in party " + politicalParty.getName());
                }

                politicalParty.addCandidate(candidate);
                politicalPartyRepository.save(politicalParty);
            }
        }
    }


    public void removeVote(Long partyId, Long candidateId) {

        Optional<PoliticalParty> politicalPartyOpt = findById(partyId);
        if (politicalPartyOpt.isPresent()) {
            PoliticalParty politicalParty = politicalPartyOpt.get();
            Optional<Candidate> candidateOpt = politicalParty.getCandidates().stream()
                    .filter(c -> c.getId().equals(candidateId))
                    .findFirst();

            if (candidateOpt.isPresent()) {
                Candidate candidate = candidateOpt.get();
                politicalParty.removeCandidate(candidate);
                politicalPartyRepository.save(politicalParty);
            }
        }
    }


    public void addVote(Long partyId) {

        Optional<PoliticalParty> politicalPartyOpt = findById(partyId);
        if (politicalPartyOpt.isPresent()) {
            PoliticalParty politicalParty = politicalPartyOpt.get();
            politicalParty.addVote();
            politicalPartyRepository.save(politicalParty);
        }
    }


    public List<Candidate> findAllCandidatesOrderedByVotesDesc(Long partyId) {
        return politicalPartyRepository.findAllCandidatesOfPartyOrderedByVotesDesc(partyId);
    }
}
