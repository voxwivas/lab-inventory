package com.labfolder.labexperimentinventory.samples.store.repository;

import com.labfolder.labexperimentinventory.samples.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long> {
}
