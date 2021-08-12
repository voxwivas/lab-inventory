package com.labfolder.labexperimentinventory.devices.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = DeviceResource.DEVICE_RESOURCE_PATH)
public class DeviceResource {
    public static final String DEVICE_RESOURCE_PATH = "/api/devices";
}
