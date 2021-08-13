package com.labfolder.labexperimentinventory.chemicals.store.repository;

import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalRepository extends JpaRepository<Chemical, Long> {
}
