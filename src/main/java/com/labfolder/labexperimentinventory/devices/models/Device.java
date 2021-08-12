package com.labfolder.labexperimentinventory.devices.models;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
