package com.labfolder.labexperimentinventory.chemicals.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chemicals")
@Getter
@NoArgsConstructor
public class Chemical {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "chemical_name")
    private String chemicalName;
    @Column(name = "flammability")
    private int flammability;
    @Column(name = "toxicity")
    private int toxicity;
    @Column(name = "acidity")
    private int acidity;
    @OneToMany(mappedBy = "chemical")
    private List<ReactivityType> reactivity;

    public Chemical(Long id, String chemicalName, int flammability, int toxicity, int acidity, List<ReactivityType> reactivity) {
        this.id = id;
        this.chemicalName = chemicalName;
        this.flammability = flammability;
        this.toxicity = toxicity;
        this.acidity = acidity;
        this.reactivity = reactivity;
    }

    public Chemical(String chemicalName, int flammability, int toxicity, int acidity, List<ReactivityType> reactivity) {
        this.chemicalName = chemicalName;
        this.flammability = flammability;
        this.toxicity = toxicity;
        this.acidity = acidity;
        this.reactivity = reactivity;
    }

    public boolean isSameChemical(Chemical chemical) {
        return this.chemicalName.equals(chemical.getChemicalName())
                && this.acidity == chemical.getAcidity()
                && this.flammability == chemical.getFlammability()
                && this.toxicity == chemical.getToxicity();
    }

    public void updateFlammability(int flammability) {
        this.flammability = flammability;
    }

    public void updateToxicity(int toxicity) {
        this.toxicity = toxicity;
    }

    public void updateAcidity(int acidity) {
        this.acidity = acidity;
    }

    public void updateReactivity(List<ReactivityType> reactivity) {
        this.reactivity = reactivity;
    }
}
