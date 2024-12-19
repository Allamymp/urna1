package org.ifpe.dws3.prova1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifpe.dws3.prova1.banco.CandidatosBanco;
import org.ifpe.dws3.prova1.banco.PartidosBanco;

@Path("/votos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_XML)
@RequestScoped
public class VotosController {

    @Inject
    private CandidatosBanco bancoCandidatos;
    @Inject
    private PartidosBanco bancoPartidos;

    @PUT
    @Path("/addVoto")
    public Response addVoto(String xmlInput) {

        Integer partidoId = extractPartidoIdFromXml(xmlInput);
        Integer candidatoId = extractCandidatoIdFromXml(xmlInput);


        Partidos partido = bancoPartidos.findById(partidoId);
        Candidatos candidato = bancoCandidatos.findById(candidatoId);

        if (partido == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Partido não encontrado com ID: " + partidoId + "\"}")
                    .build();
        }

        if (candidato == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Candidato não encontrado com ID: " + candidatoId + "\"}")
                    .build();
        }

        partido.addVoto();
        candidato.addVotos();

        bancoPartidos.save(partido);
        bancoCandidatos.save(candidato);

        return Response.ok().build();
    }

    private Integer extractPartidoIdFromXml(String xml) {
        return Integer.parseInt(xml.substring(xml.indexOf("<partidoId>") + 11, xml.indexOf("</partidoId>")));
    }

    private Integer extractCandidatoIdFromXml(String xml) {
        return Integer.parseInt(xml.substring(xml.indexOf("<candidatoId>") + 13, xml.indexOf("</candidatoId>")));
    }
}
