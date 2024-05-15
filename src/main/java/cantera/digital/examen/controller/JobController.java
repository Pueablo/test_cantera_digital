package cantera.digital.examen.controller;

import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.repository.JobRepository;
import cantera.digital.examen.service.JobService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JobController {

    @Inject
    private JobRepository jobRepository;

    @GET
    public Response listJobs() {
        List<JobEntity> jobs = jobRepository.listAll();
        return Response.ok(jobs).build();
    }
}
