package com.labfolder.labexperimentinventory.devices.store;

import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.exceptions.DeviceExistsException;
import com.labfolder.labexperimentinventory.exceptions.DeviceNotFoundException;

import java.util.*;

public class FakeDeviceStore implements DeviceStore {

    private final Map<Long, Device> devices = new HashMap<>();

    @Override
    public Long saveDevice(Device device) {
        if (checkDeviceExists(device)) {
            throw new DeviceExistsException(String.format("Device with element name %s already exixts", device.getElement()));
        } else {
            Long id = (long) nextDeviceId();
            var deviceToSave = new Device(
                    id,
                    device.getElement(),
                    device.getMassInKg(),
                    device.getLengthInMeters(),
                    device.getDiameterInMeters(),
                    device.getRemarks()
            );
            devices.put(id, deviceToSave);
            return id;
        }
    }

    @Override
    public Device updateDevice(Device device) {
        var device1 = devices.get(device.getId());
        if (device1 == null) {
            throw new DeviceNotFoundException(String.format("Device with id %n   for update does not exist", device.getId()));
        } else {
            devices.put(device.getId(), device);
        }
        return device;
    }

    @Override
    public Optional<Device> getDeviceById(Long deviceId) {
        var device1 = devices.get(deviceId);
        if (device1 == null) {
            throw new DeviceNotFoundException(String.format("Device with id %n for update does not exist", deviceId));
        } else {
            return Optional.of(devices.get(deviceId));
        }
    }

    @Override
    public Optional<List<Device>> getAllDevices() {
        return Optional.of(new ArrayList<>(devices.values()));
    }

    private boolean checkDeviceExists(Device device) {
        return devices.values().stream().anyMatch(c -> c.isSameDevice(device));
    }


    private int nextDeviceId() {
        return devices.size() + 1;
    }
}
