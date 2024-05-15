package cantera.digital.examen.controller;

import cantera.digital.examen.dto.EmployeeRangesDto;
import cantera.digital.examen.error.TechnicalException;
import cantera.digital.examen.payload.ResponseMessageTotalHoursWorked;
import cantera.digital.examen.service.EmployeeWorkedHoursService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("api/v1/getHours")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class EmployeeWorkedHoursController {

    @Inject
    private EmployeeWorkedHoursService employeeWorkedHours;

    @GET
    public Response listJobs(EmployeeRangesDto employeeRangesDto) {

        try {
            Integer hours = employeeWorkedHours.findTotalHoursForEmployee(employeeRangesDto);
            return Response.ok(ResponseMessageTotalHoursWorked.builder().
                    totalWorkedHours(hours).
                    success(true)
                    .build()).build();

        } catch (TechnicalException e) {
            log.error(e.getMessage());
            return Response.ok(ResponseMessageTotalHoursWorked.builder()
                    .totalWorkedHours(null)
                    .success(false)
                    .build()).status(Response.Status.BAD_REQUEST).build();
        }
    }
}
