package cantera.digital.examen.service.impl;

import cantera.digital.examen.dto.EmployeeRangesDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import cantera.digital.examen.error.TechnicalException;
import cantera.digital.examen.repository.EmployeeRepository;
import cantera.digital.examen.repository.EmployeeWorkedHoursRepository;
import cantera.digital.examen.service.EmployeeWorkedHoursService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmployeeWorkedHoursServiceImpl implements EmployeeWorkedHoursService {

    @Inject
    private EmployeeWorkedHoursRepository repository;

    @Inject
    EmployeeRepository employeeRepository;

    @Override
    public Integer findTotalHoursForEmployee(EmployeeRangesDto employeeRangesDto) {
        EmployeeEntity employeeWorkedHours = employeeRepository.findByEmployeeId(employeeRangesDto.getEmployeeId());
        if (employeeWorkedHours == null) {
            throw new TechnicalException("Employee ID not found");
        }

        if (employeeRangesDto.getStartDate().after(employeeRangesDto.getEndDate())) {
            throw new TechnicalException("The start date must be before the end date.");
        }

        return repository.findByEmployeeIdAndWorkedDateBetween(employeeRangesDto.getEmployeeId(), employeeRangesDto.getStartDate(), employeeRangesDto.getEndDate())
                .stream()
                .mapToInt(EmployeeWorkedHoursEntity::getWorkedHours)
                .sum();
    }
}
