package org.ifpe.dws3.prova1.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifpe.dws3.prova1.entity.Candidate;
import org.ifpe.dws3.prova1.entity.PoliticalParty;
import org.ifpe.dws3.prova1.service.PoliticalPartyService;

import java.util.List;
import java.util.Optional;

@Path("/parties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PoliticalPartyController {
    @Inject
    private PoliticalPartyService politicalPartyService;

    @GET
    public Response getAllParties() {
         List<PoliticalParty> parties = politicalPartyService.findAll();
        return Response.ok(parties).build();
    }

    @GET
    @Path("/{id}")
    public Response getPartyById(@PathParam("id") Long id) {
         return politicalPartyService.findById(id)
                .map(party -> Response.ok(party).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity("Party not found with ID: " + id)
                        .build());
    }

    @POST
    public Response createParty(@Valid PoliticalParty politicalParty) {
         PoliticalParty createdPoliticalParty = politicalPartyService.save(politicalParty);
        return Response.status(Response.Status.CREATED).entity(createdPoliticalParty).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateParty(@PathParam("id") Long id, @Valid PoliticalParty politicalParty) {
         politicalParty.setId(id);
        PoliticalParty updatedPoliticalParty = politicalPartyService.save(politicalParty);
        return Response.ok(updatedPoliticalParty).build();
    }


    @GET
    @Path("/name/{name}")
    public Response getPartyByName(@PathParam("name") String name) {
         Optional<PoliticalParty> partyOptional = politicalPartyService.findByName(name);
        if (partyOptional.isPresent()) {
             return Response.ok(partyOptional.get()).build();
        } else {
             return Response.status(Response.Status.NOT_FOUND)
                    .entity("Party not found with name: " + name)
                    .build();
        }
    }

    @GET
    @Path("/abbreviation/{abbreviation}")
    public Response getPartyByAbbreviation(@PathParam("abbreviation") String abbreviation) {
         Optional<PoliticalParty> partyOptional = politicalPartyService.findByAbbreviation(abbreviation);
        if (partyOptional.isPresent()) {
             return Response.ok(partyOptional.get()).build();
        } else {
             return Response.status(Response.Status.NOT_FOUND)
                    .entity("Party not found with abbreviation: " + abbreviation)
                    .build();
        }
    }

    @GET
    @Path("/number/{number}")
    public Response getPartyByNumber(@PathParam("number") Integer number) {
         Optional<PoliticalParty> partyOptional = politicalPartyService.findByNumber(number);
        if (partyOptional.isPresent()) {
             return Response.ok(partyOptional.get()).build();
        } else {
             return Response.status(Response.Status.NOT_FOUND)
                    .entity("Party not found with number: " + number)
                    .build();
        }
    }


    @GET
    @Path("/candidates/{partyId}/votes")
    public List<Candidate> getPartyCandidatesByVotes(@PathParam("partyId") Long partyId) {
         return politicalPartyService.findAllCandidatesOrderedByVotesDesc(partyId);
    }

    @POST
    @Path("/{partyId}/candidate")
    public Response addCandidate(@PathParam("partyId") Long partyId, Long candidateId) {
         politicalPartyService.addCandidate(partyId, candidateId);
         return Response.status(Response.Status.CREATED).entity("Candidate added successfully.").build();
    }

    @DELETE
    @Path("/{partyId}/candidate/{candidateId}")
    public Response removeCandidate(@PathParam("partyId") Long partyId, @PathParam("candidateId") Long candidateId) {
         politicalPartyService.removeVote(partyId, candidateId);
         return Response.ok("Candidate removed successfully.").build();
    }

    @POST
    @Path("/{partyId}/vote")
    public Response addVote(@PathParam("partyId") Long partyId) {
         politicalPartyService.addVote(partyId);
         return Response.ok("Vote added successfully.").build();
    }
}
