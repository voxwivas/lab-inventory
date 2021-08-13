package com.labfolder.labexperimentinventory.samples.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateSampleRequest {
    private Long id;
    private String sampleName;
    private String disease;
    private String strainOrBreed;
    private String genotype;

    public CreateSampleRequest(String sampleName, String disease, String strainOrBreed, String genotype) {
        this.sampleName = sampleName;
        this.disease = disease;
        this.strainOrBreed = strainOrBreed;
        this.genotype = genotype;
    }

    public CreateSampleRequest(Long id, String sampleName, String disease, String strainOrBreed, String genotype) {
        this.id = id;
        this.sampleName = sampleName;
        this.disease = disease;
        this.strainOrBreed = strainOrBreed;
        this.genotype = genotype;
    }

    public Sample asSample() {
        return new Sample(
                sampleName,
                disease,
                strainOrBreed,
                genotype
        );
    }

    public Sample asSampleUpdate() {
        return new Sample(
                id,
                sampleName,
                disease,
                strainOrBreed,
                genotype
        );
    }
}
