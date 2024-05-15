package cantera.digital.examen.controller;

import cantera.digital.examen.dto.EmployeeDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.service.EmployeeService;
import cantera.digital.examen.util.Constants;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;

@Path(Constants.PATH_EMPLOYEE_RESOURCE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {
    @Inject
    private EmployeeService employeeService;

    @GET
    @Path("/byJob/{jobId}")
    public List<EmployeeEntity> getEmployeesByJob(@PathParam("jobId") Long jobId) {
        return employeeService.findEmployeesByJobId(jobId);
    }

    @GET
    @Path("/filter/{jobId}/{genderId}")
    public List<EmployeeEntity> filterEmployeesByJobAndGender(@PathParam("jobId") Long jobId, @PathParam("genderId") Long genderId) {
        return employeeService.filterEmployeesByJobIdAndGender(jobId, genderId);
    }

    @GET
    @Path("/filterAndSort/{jobId}")
    public List<EmployeeEntity> filterAndSortEmployeesByJob(@PathParam("jobId") Long jobId) {
        return employeeService.filterAndSortEmployeesByJobId(jobId);
    }

    @GET
    @Path("/groupByLastName/{jobId}")
    public Map<String, List<EmployeeEntity>> groupEmployeesByLastName(@PathParam("jobId") Long jobId) {
        return employeeService.groupEmployeesByLastName(jobId);
    }

    @GET
    @Path("/all")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
