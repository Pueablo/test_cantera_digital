package cantera.digital.examen.service;

import cantera.digital.examen.dto.EmployeeRangesDto;

public interface EmployeeWorkedHoursService {
    Integer findTotalHoursForEmployee(EmployeeRangesDto employeeRangesDto);
}
