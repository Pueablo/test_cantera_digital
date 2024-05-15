package cantera.digital.examen.service;

import cantera.digital.examen.entity.JobEntity;

import java.util.List;

public interface JobService {
    List<JobEntity> findAllJobs();
}
