package cantera.digital.examen.service;

import cantera.digital.examen.dto.EmployeeRangesDto;
import cantera.digital.examen.entity.JobEntity;

import java.math.BigDecimal;
import java.util.List;

public interface JobService {
    BigDecimal calculatePayment(EmployeeRangesDto employeeRangesDto);
}
