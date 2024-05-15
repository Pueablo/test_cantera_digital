package cantera.digital.examen.service;

import cantera.digital.examen.dto.EmployeeDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.JobEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity save(EmployeeDto employeeDto);

}
