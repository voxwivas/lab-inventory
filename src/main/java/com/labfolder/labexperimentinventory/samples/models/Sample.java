package com.labfolder.labexperimentinventory.samples.models;

import javax.persistence.*;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
