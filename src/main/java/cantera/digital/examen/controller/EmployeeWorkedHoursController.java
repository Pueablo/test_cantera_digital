package cantera.digital.examen.controller;

import cantera.digital.examen.dto.WorkedHoursDto;
import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.payload.ResponseMessageEmployee;
import cantera.digital.examen.payload.ResponseMessageTotalHoursWorked;
import cantera.digital.examen.repository.JobRepository;
import cantera.digital.examen.service.EmployeeWorkedHoursService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path("api/v1/getHours")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class EmployeeWorkedHoursController {

    @Inject
    private EmployeeWorkedHoursService employeeWorkedHours;

    @GET
    public Response listJobs(WorkedHoursDto workedHoursDto) {

        try {
            Integer hours = employeeWorkedHours.findTotalHoursForEmployee(workedHoursDto);
            return Response.ok(ResponseMessageTotalHoursWorked.builder().
                    totalWorkedHours(hours).
                    success(true)
                    .build()).build();

        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.ok(ResponseMessageTotalHoursWorked.builder()
                    .totalWorkedHours(null)
                    .success(false)
                    .build()).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
