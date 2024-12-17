package org.ifpe.dws3.prova1.repository;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.ifpe.dws3.prova1.entity.Candidate;

import java.util.List;
import java.util.Optional;

@Stateless
public class CandidateRepository {

    @PersistenceContext
    EntityManager em;

    public Candidate save(Candidate candidate) {
        if (candidate.getId() == null) {
            em.persist(candidate);
        } else {
            em.merge(candidate);
        }
        return candidate;
    }

    public List<Candidate> findAll() {
        return em.createQuery("SELECT c FROM Candidate c", Candidate.class)
                .getResultList();
    }

    public Optional<Candidate> findById(Long id) {
        Candidate candidate = em.find(Candidate.class, id);
        return Optional.ofNullable(candidate);
    }

    public void remove(Long id) {
        Optional<Candidate> candidateOptional = findById(id);
        candidateOptional.ifPresent(candidate -> em.remove(candidate));
    }

    public List<Candidate> findAllOrderedByVotesDesc() {
        return em.createQuery(
                        "SELECT c FROM Candidate c ORDER BY c.vote DESC", Candidate.class)
                .getResultList();
    }
}
