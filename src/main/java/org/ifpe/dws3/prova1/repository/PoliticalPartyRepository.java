package org.ifpe.dws3.prova1.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;
import org.ifpe.dws3.prova1.entity.Candidate;
import org.ifpe.dws3.prova1.entity.PoliticalParty;

import java.util.List;
import java.util.Optional;

@Stateless
public class PoliticalPartyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PoliticalParty save(PoliticalParty politicalParty) {
        if (politicalParty.getId() == null) {
            entityManager.persist(politicalParty);
        } else {
            entityManager.merge(politicalParty);
        }
        return politicalParty;
    }

    public List<PoliticalParty> findAll() {
        return entityManager.createQuery("SELECT p FROM PoliticalParty p", PoliticalParty.class)
                .getResultList();
    }

    public Optional<PoliticalParty> findById(Long id) {
        return Optional.ofNullable(entityManager.find(PoliticalParty.class, id));
    }

    public Optional<PoliticalParty> findByName(String name) {
        try {
            PoliticalParty party = entityManager.createQuery("SELECT p FROM PoliticalParty p WHERE p.name = :name", PoliticalParty.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(party);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<PoliticalParty> findByNumber(Integer number) {
        try {
            PoliticalParty party = entityManager.createQuery("SELECT p FROM PoliticalParty p WHERE p.number = :number", PoliticalParty.class)
                    .setParameter("number", number)
                    .getSingleResult();
            return Optional.of(party);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<PoliticalParty> findByAbbreviation(String abbreviation) {
        try {
            PoliticalParty party = entityManager.createQuery("SELECT p FROM PoliticalParty p WHERE p.abbreviation = :abbreviation", PoliticalParty.class)
                    .setParameter("abbreviation", abbreviation)
                    .getSingleResult();
            return Optional.of(party);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<PoliticalParty> findAllOrderedByVotesDesc() {
        return entityManager.createQuery("SELECT p FROM PoliticalParty p ORDER BY p.vote DESC", PoliticalParty.class)
                .getResultList();
    }

    public List<Candidate> findAllCandidatesOfPartyOrderedByVotesDesc(Long partyId) {
        return entityManager.createQuery("SELECT c FROM Candidate c WHERE c.party.id = :partyId ORDER BY c.vote DESC", Candidate.class)
                .setParameter("partyId", partyId)
                .getResultList();
    }
}
