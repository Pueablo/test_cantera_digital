package cantera.digital.examen.repository;

import cantera.digital.examen.entity.EmployeeEntity;
import cantera.digital.examen.entity.EmployeeWorkedHoursEntity;
import cantera.digital.examen.entity.JobEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<EmployeeEntity> {

    public EmployeeEntity findByEmployeeId(Long employeeId) {
        return find("employeeId", employeeId).firstResult();
    }

    public List<EmployeeEntity> findByJobId(Long jobId) {
        return list("jobId", jobId);
    }
}
