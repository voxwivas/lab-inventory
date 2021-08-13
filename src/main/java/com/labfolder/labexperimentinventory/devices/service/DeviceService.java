package com.labfolder.labexperimentinventory.devices.service;

import com.labfolder.labexperimentinventory.devices.models.CreateDeviceRequest;
import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.devices.store.DeviceStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceStore deviceStore;

    public Long createDevice(CreateDeviceRequest createDeviceRequest) {
        return deviceStore.saveDevice(createDeviceRequest.asDevice());
    }

    public Optional<Device> getDeviceById(Long deviceId) {
        return deviceStore.getDeviceById(deviceId);
    }

    public Device updateDevice(CreateDeviceRequest updateDeviceRequest) {
        return deviceStore.updateDevice(updateDeviceRequest.asUpdatedDevice());
    }

    public Optional<List<Device>> geAllDevices() {
        return deviceStore.getAllDevices();
    }
}
