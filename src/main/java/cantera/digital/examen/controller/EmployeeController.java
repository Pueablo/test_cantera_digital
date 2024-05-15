package cantera.digital.examen.controller;

import cantera.digital.examen.dto.EmployeeDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.payload.ResponseMessageEmployee;
import cantera.digital.examen.repository.JobRepository;
import cantera.digital.examen.service.EmployeeService;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.annotations.Pos;

import java.util.List;

@Path("api/v1/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @GET
    public Response listEmployee() {
        List<EmployeeEntity> jobs = employeeService.findAllEmployee();
        return Response.ok(jobs).build();
    }

    @POST
    public Response createEmployee(EmployeeDto employee){
        try {
            EmployeeEntity employeeEntity = employeeService.save(employee);
            return Response.ok(ResponseMessageEmployee.builder()
                    .id(employeeEntity.getId())
                    .success(true)
                    .build()).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.ok(ResponseMessageEmployee.builder()
                    .id(null)
                    .success(false)
                    .build()).status(Response.Status.BAD_REQUEST).build();
        }
    }
}