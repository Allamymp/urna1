package org.ifpe.dws3.prova1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifpe.dws3.prova1.banco.CandidatosBanco;
import org.ifpe.dws3.prova1.banco.PartidosBanco;

import java.util.List;

@Path("/partidos")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response getById(String xmlInput) {

        Integer id = extractIdFromXml(xmlInput);
        Partidos partido = banco.findById(id);

        if (partido == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Partido não encontrado com ID: " + id + "\"}")
                    .build();
        }
        return Response.ok(partido).build();
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

    private Integer extractIdFromXml(String xml) {
        try {
            int start = xml.indexOf("<id>") + 4;
            int end = xml.indexOf("</id>");
            String idString = xml.substring(start, end);
            return Integer.parseInt(idString);
        } catch (Exception e) {
            return null;
        }
    }
}
