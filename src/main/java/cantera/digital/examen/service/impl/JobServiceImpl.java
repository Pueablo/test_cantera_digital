package cantera.digital.examen.service.impl;

import cantera.digital.examen.dto.EmployeeRangesDto;
import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.error.TechnicalException;
import cantera.digital.examen.repository.EmployeeRepository;
import cantera.digital.examen.repository.EmployeeWorkedHoursRepository;
import cantera.digital.examen.repository.JobRepository;
import cantera.digital.examen.service.JobService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class JobServiceImpl implements JobService {

    @Inject
    private EmployeeWorkedHoursRepository employeeWorkedHoursRepository;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private JobRepository jobRepository;

    @Override
    public BigDecimal calculatePayment(EmployeeRangesDto employeeRangesDto) {
        EmployeeEntity employee = employeeRepository.findById(employeeRangesDto.getEmployeeId());
        if (employee == null) {
            throw new TechnicalException("Employee ID not found");
        }

        if (employeeRangesDto.getStartDate().after(employeeRangesDto.getEndDate())) {
            throw new TechnicalException("The start date must be before the end date");
        }

        List<EmployeeWorkedHoursEntity> workedHours = employeeWorkedHoursRepository.find("employeeId = ?1 and workedDate between ?2 and ?3",
                employeeRangesDto.getEmployeeId(), employeeRangesDto.getStartDate(), employeeRangesDto.getEndDate()).list();

        BigDecimal totalSalary = BigDecimal.ZERO;
        for (EmployeeWorkedHoursEntity workedHour : workedHours) {
            JobEntity job = jobRepository.findById(employee.getJobId());
            totalSalary = totalSalary.add(job.getSalary().multiply(BigDecimal.valueOf(workedHour.getWorkedHours())));
        }

        return totalSalary;
    }
}
