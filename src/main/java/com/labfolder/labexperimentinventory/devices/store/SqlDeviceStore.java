package com.labfolder.labexperimentinventory.devices.store;

import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.devices.store.repository.DeviceRepository;
import com.labfolder.labexperimentinventory.exceptions.DeviceExistsException;
import com.labfolder.labexperimentinventory.exceptions.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SqlDeviceStore implements DeviceStore {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Long saveDevice(Device device) {
        if (checkDeviceExists(device)) {
            throw new DeviceExistsException(String.format("Device with element name %s already exixts", device.getElement()));
        }
        return deviceRepository.save(device).getId();
    }

    @Override
    public Device updateDevice(Device device) {
        var device1 = deviceRepository.findById(device.getId());
        if (device1.isPresent()) {
            return deviceRepository.save(device);
        }
        throw new DeviceNotFoundException(String.format("Device with id %n   for update does not exist", device.getId()));
    }

    @Override
    public Optional<Device> getDeviceById(Long deviceId) {
        var device1 = deviceRepository.findById(deviceId);
        if (device1.isPresent()) {
            return device1;
        }
        throw new DeviceNotFoundException(String.format("Device with id %n   for update does not exist", device1));
    }

    @Override
    public Optional<List<Device>> getAllDevices() {
        return Optional.of(deviceRepository.findAll());
    }

    private boolean checkDeviceExists(Device device) {
        return deviceRepository.findAll().stream().anyMatch(c -> c.isSameDevice(device));
    }
}
