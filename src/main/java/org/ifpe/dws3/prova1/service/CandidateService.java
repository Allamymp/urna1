package org.ifpe.dws3.prova1.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ifpe.dws3.prova1.entity.Candidate;
import org.ifpe.dws3.prova1.repository.CandidateRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CandidateService {

    @Inject
    private CandidateRepository candidateRepository;



    public Candidate create(Candidate candidate) {

        return candidateRepository.save(candidate);
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }


    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    public void delete(Long id) {
        Optional<Candidate> candidateOpt = findById(id);
        candidateOpt.ifPresent(candidate -> {
            candidateRepository.remove(candidate.getId());
        });
    }

    public Candidate addVote(Long id) {

        Optional<Candidate> candidateOpt = findById(id);
        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            candidate.addVote();
             return candidateRepository.save(candidate);
        } else {
             throw new IllegalArgumentException("Candidate not found with ID: " + id);
        }
    }

    public List<Candidate> findAllOrderedByVotes() {
         return candidateRepository.findAllOrderedByVotesDesc();
    }


    public Candidate update(Candidate candidate) {


        Optional<Candidate> existingCandidateOpt = findById(candidate.getId());
        if (existingCandidateOpt.isPresent()) {
            Candidate existingCandidate = existingCandidateOpt.get();

            existingCandidate.setName(candidate.getName());
            existingCandidate.setNumber(candidate.getNumber());


            return candidateRepository.save(existingCandidate);
        } else {
             throw new IllegalArgumentException("Candidate not found with ID: " + candidate.getId());
        }
    }


}
