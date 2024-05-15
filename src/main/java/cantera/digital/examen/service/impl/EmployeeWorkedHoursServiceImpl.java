package cantera.digital.examen.service.impl;

import cantera.digital.examen.dto.WorkedHoursDto;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import cantera.digital.examen.repository.EmployeeWorkedHoursRepository;
import cantera.digital.examen.service.EmployeeWorkedHoursService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class EmployeeWorkedHoursServiceImpl implements EmployeeWorkedHoursService {

    @Inject
    private EmployeeWorkedHoursRepository repository;

    @Override
    public Integer findTotalHoursForEmployee(WorkedHoursDto workedHoursDto) {
        EmployeeWorkedHoursEntity employeeWorkedHours = repository.findByEmployeeId(workedHoursDto.getEmployeeId());
        if (employeeWorkedHours == null) {
            throw new IllegalStateException("Employee ID not found");
        }

        if (workedHoursDto.getStartDate().after(workedHoursDto.getEndDate())) {
            throw new IllegalArgumentException("The start date must be before the end date.");

        }

        return repository.findByEmployeeIdAndWorkedDateBetween(workedHoursDto.getEmployeeId(), workedHoursDto.getStartDate(), workedHoursDto.getEndDate())
                .stream()
                .mapToInt(EmployeeWorkedHoursEntity::getWorkedHours)
                .sum();
    }
}
