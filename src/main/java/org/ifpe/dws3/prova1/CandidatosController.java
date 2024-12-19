package org.ifpe.dws3.prova1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ifpe.dws3.prova1.banco.CandidatosBanco;

import java.util.List;

@Path("/candidatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CandidatosController {

    @Inject
    private CandidatosBanco banco;

    @GET
    public List<Candidatos> getAll() {
        return banco.findAll();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Candidatos getById(CandidatoRequest request) {
        Integer id = request.getId();

        Candidatos candidato = banco.findById(id);

        if (candidato == null) {
            throw new WebApplicationException("Candidato não encontrado com ID: " + id, 404);
        }

        return candidato;
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

    public static class CandidatoRequest {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

}
