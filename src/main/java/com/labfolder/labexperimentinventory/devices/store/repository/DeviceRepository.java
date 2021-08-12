package com.labfolder.labexperimentinventory.devices.store.repository;

import com.labfolder.labexperimentinventory.devices.models.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device,Long> {
}
