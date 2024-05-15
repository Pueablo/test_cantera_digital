package cantera.digital.examen.controller;

import cantera.digital.examen.dto.EmployeeRangesDto;
import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.error.TechnicalException;
import cantera.digital.examen.payload.ResponseMessagePaymentEmployee;
import cantera.digital.examen.payload.ResponseMessageTotalHoursWorked;
import cantera.digital.examen.repository.JobRepository;
import cantera.digital.examen.service.JobService;
import cantera.digital.examen.util.Constants;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Path(Constants.PATH_JOB)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class JobController {

    @Inject
    private JobService service;

    @GET
    @Path("/calculate")
    public Response calculatePayment(EmployeeRangesDto employeeRangesDto) {
        try {
            return Response.ok(ResponseMessagePaymentEmployee.builder().
                    payment(service.calculatePayment(employeeRangesDto)).
                    success(true)
                    .build()).build();
        } catch (TechnicalException e) {
            log.error(e.getMessage());
            return Response.ok(ResponseMessagePaymentEmployee.builder().
                    payment(null).
                    success(false)
                    .build()).build();
        }
    }
}
