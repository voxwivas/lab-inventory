package com.labfolder.labexperimentinventory.chemicals.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reactivity_type")
@Getter
@NoArgsConstructor
public class ReactivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "chemical_id", nullable = false)
    private Chemical chemical;

    public ReactivityType(Long id, String name, Chemical chemical) {
        this.id = id;
        this.name = name;
        this.chemical = chemical;
    }

    public ReactivityType(String name, Chemical chemical) {
        this.name = name;
        this.chemical = chemical;
    }
}