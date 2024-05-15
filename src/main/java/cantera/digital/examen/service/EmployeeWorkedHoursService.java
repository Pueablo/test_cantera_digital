package cantera.digital.examen.service;

import cantera.digital.examen.dto.WorkedHoursDto;
import cantera.digital.examen.entity.JobEntity;

import java.util.List;

public interface EmployeeWorkedHoursService {
    Integer findTotalHoursForEmployee(WorkedHoursDto workedHoursDto);
}
