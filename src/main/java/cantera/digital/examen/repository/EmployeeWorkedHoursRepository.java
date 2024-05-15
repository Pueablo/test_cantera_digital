package cantera.digital.examen.repository;

import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class EmployeeWorkedHoursRepository implements PanacheRepository<EmployeeWorkedHoursEntity> {

    public List<EmployeeWorkedHoursEntity> findByEmployeeIdAndWorkedDateBetween(Long employeeId, Date startDate, Date endDate) {
        return find("employeeId = ?1 and workedDate between ?2 and ?3", employeeId, startDate, endDate).list();
    }

    public EmployeeWorkedHoursEntity findByEmployeeId(Long employeeId) {
        return find("employeeId", employeeId).firstResult();
    }
}
