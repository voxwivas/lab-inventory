package com.labfolder.labexperimentinventory.devices.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDeviceRequest {
    private Long id;
    private String element;
    private int massInKg;
    private int lengthInMeters;
    private int diameterInMeters;
    private String remarks;

    public CreateDeviceRequest(String element, int massInKg, int lengthInMeters, int diameterInMeters, String remarks) {
        this.element = element;
        this.massInKg = massInKg;
        this.lengthInMeters = lengthInMeters;
        this.diameterInMeters = diameterInMeters;
        this.remarks = remarks;
    }

    public CreateDeviceRequest(Long id, String element, int massInKg, int lengthInMeters, int diameterInMeters, String remarks) {
        this.id = id;
        this.element = element;
        this.massInKg = massInKg;
        this.lengthInMeters = lengthInMeters;
        this.diameterInMeters = diameterInMeters;
        this.remarks = remarks;
    }

    public Device asDevice() {
        return new Device(
                element,
                massInKg,
                lengthInMeters,
                diameterInMeters,
                remarks
        );
    }

    public Device asUpdatedDevice() {
        return new Device(
                id,
                element,
                massInKg,
                lengthInMeters,
                diameterInMeters,
                remarks
        );
    }
}
