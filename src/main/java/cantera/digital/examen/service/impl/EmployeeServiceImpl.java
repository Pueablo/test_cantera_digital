package cantera.digital.examen.service.impl;

import cantera.digital.examen.dto.EmployeeDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.repository.EmployeeRepository;
import cantera.digital.examen.service.EmployeeService;
import cantera.digital.examen.service.JobService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository repository;

    @Override
    public List<EmployeeEntity> findAllEmployee() {
        return repository.listAll();
    }

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeDto employeeDto) {

        EmployeeEntity employee = EmployeeEntity.builder().
                genderId(employeeDto.getGenderId()).
                jobId(employeeDto.getJobId()).
                name(employeeDto.getName()).
                lastName(employeeDto.getLastName()).
                birthdate(employeeDto.getBirthdate()).
                build();

        if (!employee.validateBirthday()){
            throw new IllegalArgumentException("You are underage!");
        }

        repository.persist(employee);
        return employee;
    }
}
