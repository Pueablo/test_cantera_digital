package cantera.digital.examen.service;

import cantera.digital.examen.dto.EmployeeDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.JobEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    EmployeeEntity save(EmployeeDto employeeDto);

    List<EmployeeEntity> findEmployeesByJobId(Long jobId);
    List<EmployeeEntity> filterEmployeesByJobIdAndGender(Long jobId, Long genderId);

    List<EmployeeEntity> filterAndSortEmployeesByJobId(Long jobId);

    Map<String, List<EmployeeEntity>> groupEmployeesByLastName(Long jobId);

    List<EmployeeDto> getAllEmployees();
}
