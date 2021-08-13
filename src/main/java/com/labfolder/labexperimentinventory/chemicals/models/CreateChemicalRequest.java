package com.labfolder.labexperimentinventory.chemicals.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateChemicalRequest {
    private Long id;
    private String chemicalName;
    private int flammability;
    private int toxicity;
    private int acidity;
    private List<ReactivityType> reactivity = new ArrayList<>();

    public CreateChemicalRequest(String chemicalName, int flammability, int toxicity, int acidity, List<ReactivityType> reactivity) {
        this.chemicalName = chemicalName;
        this.flammability = flammability;
        this.toxicity = toxicity;
        this.acidity = acidity;
        this.reactivity = reactivity;
    }

    public CreateChemicalRequest(Long id, String chemicalName, int flammability, int toxicity, int acidity, List<ReactivityType> reactivity) {
        this.id = id;
        this.chemicalName = chemicalName;
        this.flammability = flammability;
        this.toxicity = toxicity;
        this.acidity = acidity;
        this.reactivity = reactivity;
    }

    public Chemical asChemical() {
        return new Chemical(
                chemicalName,
                flammability,
                toxicity,
                acidity,
                reactivity
        );
    }

    public Chemical asChemicalUpdate() {
        return new Chemical(
                id,
                chemicalName,
                flammability,
                toxicity,
                acidity,
                reactivity
        );
    }

}
