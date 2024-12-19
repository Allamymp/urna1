package org.ifpe.dws3.prova1.banco;

import jakarta.ejb.Stateless;
import org.ifpe.dws3.prova1.Candidatos;
import org.ifpe.dws3.prova1.Partidos;

import java.util.*;

@Stateless
public class PartidosBanco {

    private final List<Partidos> partidosList = new ArrayList<>();

    public Partidos save(Partidos partido) {
        if (partido.getId() == null) {
            int novoId = partidosList.isEmpty() ? 1 : partidosList.get(partidosList.size() - 1).getId() + 1;
            partido.setId(novoId);
            partidosList.add(partido);
        } else {
            delete(partido.getId());
            partidosList.add(partido);
        }
        return partido;
    }

    public List<Partidos> findAll() {
        return new ArrayList<>(partidosList);
    }

    public Partidos findById(Integer id) {
        return partidosList.stream()
                .filter(partido -> Objects.equals(partido.getId(), id))
                .findFirst()
                .orElse(null);
    }


    public List<Candidatos> findAllCandidatos(Integer partidoId) {
        Partidos partido = this.findById(partidoId);
        return partido.getCandidatos();

    }

    public void delete(Integer id) {
        partidosList.removeIf(partido -> Objects.equals(partido.getId(), id));
    }
}
