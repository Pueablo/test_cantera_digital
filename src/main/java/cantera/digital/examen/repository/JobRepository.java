package cantera.digital.examen.repository;

import cantera.digital.examen.entity.JobEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JobRepository implements PanacheRepository<JobEntity> {

}
