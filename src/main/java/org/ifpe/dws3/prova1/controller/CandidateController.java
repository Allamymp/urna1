package org.ifpe.dws3.prova1.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifpe.dws3.prova1.entity.Candidate;
import org.ifpe.dws3.prova1.service.CandidateService;

import java.util.List;

@Path("/candidates")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CandidateController {

    @Inject
    private CandidateService candidateService;


    @GET
    public Response getAll() {

        List<Candidate> candidates = candidateService.findAll();
        return Response.status(Response.Status.OK).entity(candidates).build();
    }


    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
         Candidate candidate = candidateService.findById(id)
                .orElseThrow(() -> new WebApplicationException("Candidate not found with ID: " + id, Response.Status.NOT_FOUND));
        return Response.status(Response.Status.OK).entity(candidate).build();
    }


    @POST
    public Response create(Candidate candidate) {
         Candidate createdCandidate = candidateService.create(candidate);
        return Response.status(Response.Status.CREATED).entity(createdCandidate).build();
    }


    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Candidate candidate) {
         Candidate updatedCandidate = candidateService.update(candidate);
        return Response.status(Response.Status.OK).entity(updatedCandidate).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long id) {
         candidateService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @GET
    @Path("/ordenados-votos")
    public Response getAllOrderedByVotes() {
         List<Candidate> candidates = candidateService.findAllOrderedByVotes();
        return Response.status(Response.Status.OK).entity(candidates).build();
    }


    @POST
    @Path("/{id}/voto")
    public Response addVote(@PathParam("id") Long id) {
         Candidate updatedCandidate = candidateService.addVote(id);
        return Response.status(Response.Status.OK).entity(updatedCandidate).build();
    }
}
