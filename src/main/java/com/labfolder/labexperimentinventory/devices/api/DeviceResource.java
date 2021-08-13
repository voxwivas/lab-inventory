package com.labfolder.labexperimentinventory.devices.api;

import com.labfolder.labexperimentinventory.devices.models.CreateDeviceRequest;
import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.devices.service.DeviceService;
import com.labfolder.labexperimentinventory.utils.wrappers.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = DeviceResource.DEVICE_RESOURCE_PATH)
public class DeviceResource {
    public static final String DEVICE_RESOURCE_PATH = "/api/devices";

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Id> createDevice(@RequestBody CreateDeviceRequest createDeviceRequest) {
        return ResponseEntity.ok(new Id(deviceService.createDevice(createDeviceRequest)));
    }

    @GetMapping(value = "/{deviceId}")
    public ResponseEntity<Optional<Device>> getDeviceById(@PathVariable("deviceId") Long deviceId) {
        return ResponseEntity.ok(deviceService.getDeviceById(deviceId));
    }

    @PutMapping
    public ResponseEntity<Device> updateDevice(@RequestBody CreateDeviceRequest updateDeviceRequest) {
        return ResponseEntity.ok(deviceService.updateDevice(updateDeviceRequest));
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.of(deviceService.geAllDevices());
    }
}
