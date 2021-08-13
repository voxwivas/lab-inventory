package com.labfolder.labexperimentinventory.samples.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "samples")
@Getter
@NoArgsConstructor
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "sample_name")
    private String sampleName;
    @Column(name = "disease")
    private String disease;
    @Column(name = "strain_or_breed")
    private String strainOrBreed;
    @Column(name = "genotype")
    private String genotype;

    public Sample(String sampleName, String disease, String strainOrBreed, String genotype) {
        this.sampleName = sampleName;
        this.disease = disease;
        this.strainOrBreed = strainOrBreed;
        this.genotype = genotype;
    }

    public Sample(Long id, String sampleName, String disease, String strainOrBreed, String genotype) {
        this.id = id;
        this.sampleName = sampleName;
        this.disease = disease;
        this.strainOrBreed = strainOrBreed;
        this.genotype = genotype;
    }

    public void updateSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public void updateDisease(String disease) {
        this.disease = disease;
    }

    public void updateStrainOrBreed(String strainOrBreed) {
        this.strainOrBreed = strainOrBreed;
    }

    public void updateGenotype(String genotype) {
        this.genotype = genotype;
    }

    public boolean isSameSample(Sample sample) {
        return this.disease.equals(sample.getDisease())
                && this.genotype.equals(sample.getGenotype())
                && this.strainOrBreed.equals(sample.getStrainOrBreed());
    }
}
