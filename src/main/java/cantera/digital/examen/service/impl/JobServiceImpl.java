package cantera.digital.examen.service.impl;

import cantera.digital.examen.entity.JobEntity;
import cantera.digital.examen.service.JobService;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class JobServiceImpl implements JobService {

    @Override
    public List<JobEntity> findAllJobs() {
        return JobEntity.listAll();
    }
}
