package com.labfolder.labexperimentinventory.devices.store;

import com.labfolder.labexperimentinventory.devices.models.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceStore {

    Long saveDevice(Device device);

    Device updateDevice(Device device);

    Optional<Device> getDeviceById(Long deviceId);

    Optional<List<Device>> getAllDevices();

}
