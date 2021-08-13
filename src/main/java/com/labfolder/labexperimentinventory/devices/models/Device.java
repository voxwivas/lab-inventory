package com.labfolder.labexperimentinventory.devices.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@Getter
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "element")
    private String element;
    @Column(name = "mass_in_kgs")
    private int massInKg;
    @Column(name = "length_in_meters")
    private int lengthInMeters;
    @Column(name = "diameter_in_meters")
    private int diameterInMeters;
    @Column(name = "remarks")
    private String remarks;

    public Device(String element, int massInKg, int lengthInMeters, int diameterInMeters, String remarks) {
        this.element = element;
        this.massInKg = massInKg;
        this.lengthInMeters = lengthInMeters;
        this.diameterInMeters = diameterInMeters;
        this.remarks = remarks;
    }

    public Device(Long id, String element, int massInKg, int lengthInMeters, int diameterInMeters, String remarks) {
        this.id = id;
        this.element = element;
        this.massInKg = massInKg;
        this.lengthInMeters = lengthInMeters;
        this.diameterInMeters = diameterInMeters;
        this.remarks = remarks;
    }

    public boolean isSameDevice(Device device) {
        return this.element.equals(device.element)
                && this.massInKg == device.massInKg
                && this.lengthInMeters == device.lengthInMeters
                && this.diameterInMeters == device.diameterInMeters;
    }

    public void updateRemarks(String remarks) {
        this.remarks = remarks;
    }
}
