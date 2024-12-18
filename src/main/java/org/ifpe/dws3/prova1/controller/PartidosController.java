package org.ifpe.dws3.prova1.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ifpe.dws3.prova1.banco.CandidatosBanco;
import org.ifpe.dws3.prova1.banco.PartidosBanco;
import org.ifpe.dws3.prova1.entity.Candidatos;
import org.ifpe.dws3.prova1.entity.Partidos;

import java.util.List;

@Path("/partidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PartidosController {

    @Inject
    private PartidosBanco banco;
    @Inject
    private CandidatosBanco banco_candidatos;

    @GET
    public List<Partidos> getAll() {
        return banco.findAll();

    }

    @GET
    @Path("/{id}")
    public Partidos getById(@PathParam("id") Integer id) {
        return banco.findById(id);
    }

    @POST
    public Partidos create(@Valid Partidos partidos) {
        return banco.save(partidos);
    }

    @PUT
    @Path("/{id}")
    public Partidos update(Partidos partido) {
        return banco.save(partido);
    }


    @POST
    @Path("/{partidoId}/candidato/{candidatoId}")
    public Partidos addCandidate(@PathParam("partidoId") Integer partidoId, Integer candidatoId) {

        Candidatos candidato = banco_candidatos.findById(candidatoId);
        Partidos partido = banco.findById(partidoId);

        partido.addCandidato(candidato);
        candidato.setPartido_num(partido.getNumero());
        banco_candidatos.save(candidato);
        return banco.save(partido);
    }

    @PUT
    @Path("/{partidoId}/candidato/{candidatoId}")
    public Partidos removeCandidato(@PathParam("partidoId") Integer partidoId, @PathParam("candidatoId") Integer candidatoId) {
        Candidatos candidato = banco_candidatos.findById(candidatoId);
        Partidos partido = banco.findById(partidoId);

        partido.removerCandidato(candidato);
        candidato.setPartido_num(null);
        banco_candidatos.save(candidato);
        return banco.save(partido);
    }

    @POST
    @Path("/{partidoId}/voto")
    public Partidos addVoto(@PathParam("partidoId") Integer partidoId) {

        Partidos partido = banco.findById(partidoId);

        partido.addVoto();
        return banco.save(partido);
    }
}
