package cantera.digital.examen.service.impl;

import cantera.digital.examen.dto.EmployeeRangesDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import cantera.digital.examen.error.TechnicalException;
import cantera.digital.examen.repository.EmployeeRepository;
import cantera.digital.examen.repository.EmployeeWorkedHoursRepository;
import cantera.digital.examen.service.EmployeeWorkedHoursService;
import cantera.digital.examen.util.Constants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmployeeWorkedHoursServiceImpl implements EmployeeWorkedHoursService {

    @Inject
    private EmployeeWorkedHoursRepository repository;

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Integer findTotalHoursForEmployee(EmployeeRangesDto employeeRangesDto) {
        EmployeeEntity employeeWorkedHours = employeeRepository.findByEmployeeId(employeeRangesDto.getEmployeeId());
        if (employeeWorkedHours == null) {
            throw new TechnicalException(Constants.EMPLOYEE_NOT_FOUND);
        }

        if (employeeRangesDto.getStartDate().after(employeeRangesDto.getEndDate())) {
            throw new TechnicalException(Constants.DATA_FORMAT_ERROR);
        }

        return repository.findByEmployeeIdAndWorkedDateBetween(employeeRangesDto.getEmployeeId(), employeeRangesDto.getStartDate(), employeeRangesDto.getEndDate())
                .stream()
                .mapToInt(EmployeeWorkedHoursEntity::getWorkedHours)
                .sum();
    }
}
