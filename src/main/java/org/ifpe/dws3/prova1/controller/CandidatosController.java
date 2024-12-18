package org.ifpe.dws3.prova1.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifpe.dws3.prova1.banco.CandidatosBanco;
import org.ifpe.dws3.prova1.entity.Candidatos;

import java.util.List;

@Path("/candidatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CandidatosController {

    @Inject
    CandidatosBanco banco;

    @GET
    public List<Candidatos> getAll() {
        return banco.findAll();
    }


    @GET
    @Path("/{id}")
    public Candidatos getById(@PathParam("id") Integer id) {
        return banco.findById(id);
    }


    @POST
    public Candidatos create(Candidatos candidato) {
         return banco.save(candidato);
    }


    @PUT
    @Path("/{id}")
    public Candidatos update(Candidatos candidato) {
         return banco.save(candidato);
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Integer id) {
        banco.delete(id);
        return Response.noContent().build();
    }


    @POST
    @Path("/{id}/voto")
    public Response addVote(@PathParam("id") Long id) {

        return Response.noContent().build();
    }
}
