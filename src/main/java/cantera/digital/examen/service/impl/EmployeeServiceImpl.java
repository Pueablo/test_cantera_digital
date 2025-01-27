package cantera.digital.examen.service.impl;

import cantera.digital.examen.dto.EmployeeDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.error.TechnicalException;
import cantera.digital.examen.repository.EmployeeRepository;
import cantera.digital.examen.repository.EmployeeWorkedHoursRepository;
import cantera.digital.examen.repository.JobRepository;
import cantera.digital.examen.service.EmployeeService;
import cantera.digital.examen.service.JobService;
import cantera.digital.examen.util.Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository repository;

    @Inject
    private JobRepository jobRepository;

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeDto employeeDto) {

        Optional<JobEntity> job = jobRepository.findByIdOptional(employeeDto.getJobId());

        if (job.isEmpty()){
            throw new TechnicalException(Constants.JOB_NOT_EXIST);
        }

        EmployeeEntity employee = EmployeeEntity.builder().
                genderId(employeeDto.getGenderId()).
                jobId(employeeDto.getJobId()).
                name(employeeDto.getName()).
                lastName(employeeDto.getLastName()).
                birthdate(employeeDto.getBirthdate()).
                build();

        if (!employee.validateBirthday()){
            throw new TechnicalException(Constants.UNDERAGE);
        }

        repository.persist(employee);
        return employee;
    }

    @Override
    public List<EmployeeEntity> findEmployeesByJobId(Long jobId) {
        return repository.findByJobId(jobId);
    }

    @Override
    public List<EmployeeEntity> filterEmployeesByJobIdAndGender(Long jobId, Long genderId) {
        return repository.find("jobId = ?1 and genderId = ?2", jobId, genderId).list();
    }

    @Override
    public List<EmployeeEntity> filterAndSortEmployeesByJobId(Long jobId) {
        return repository.find("jobId", jobId).stream()
                .sorted(Comparator.comparing(EmployeeEntity::getLastName))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<EmployeeEntity>> groupEmployeesByLastName(Long jobId) {
        return repository.find("jobId", jobId).stream()
                .collect(Collectors.groupingBy(EmployeeEntity::getLastName));
    }

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employees = repository.listAll();
        List<EmployeeDto> employeeDTOs = new ArrayList<>();

        employees.parallelStream().forEach(employee -> {
            EmployeeDto employeeDto = EmployeeDto.builder().
                    genderId(employee.getGenderId()).
                    jobId(employee.getJobId()).
                    name(employee.getName()).
                    lastName(employee.getName()).
                    build();
            employeeDTOs.add(employeeDto);
        });

        return employeeDTOs;
    }
}
