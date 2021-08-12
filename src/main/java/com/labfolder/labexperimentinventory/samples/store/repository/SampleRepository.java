package com.labfolder.labexperimentinventory.samples.store.repository;

import com.labfolder.labexperimentinventory.samples.models.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends CrudRepository<Sample, Long> {
}
