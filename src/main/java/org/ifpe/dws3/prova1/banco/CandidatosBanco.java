package org.ifpe.dws3.prova1.banco;


import org.ifpe.dws3.prova1.entity.Candidatos;

import java.util.*;


public  class CandidatosBanco {

    private static final List<Candidatos> candidatosList = Collections.synchronizedList(new ArrayList<>());

    public Candidatos save(Candidatos candidato) {
        if (candidato.getId() == null) {
             int novoId = candidatosList.isEmpty() ? 1 : candidatosList.get(candidatosList.size() - 1).getId() + 1;
            candidato.setId(novoId);
            candidatosList.add(candidato);
        } else {
            delete(candidato.getId());
            candidatosList.add(candidato);
        }
        return candidato;
    }

    public List<Candidatos> findAll() {
         return new ArrayList<>(candidatosList);
    }

    public Candidatos findById(Integer id) {
        return candidatosList.stream()
                .filter(candidato -> Objects.equals(candidato.getId(), id))
                .findFirst()
                .orElse(null);
    }


    public void delete(Integer id) {
         candidatosList.removeIf(candidato -> Objects.equals(candidato.getId(), id));
    }

    public void addVoto(Integer id) {
        Candidatos candidato = findById(id);
        if (candidato != null) {
            candidato.setVotos(candidato.getVotos() + 1);
        }
    }
}
