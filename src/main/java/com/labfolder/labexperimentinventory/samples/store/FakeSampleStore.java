package com.labfolder.labexperimentinventory.samples.store;

import com.labfolder.labexperimentinventory.exceptions.SampleExistsException;
import com.labfolder.labexperimentinventory.exceptions.SampleNotFoundException;
import com.labfolder.labexperimentinventory.samples.models.Sample;

import java.util.*;

public class FakeSampleStore implements SampleStore {

    private final Map<Long, Sample> samples = new HashMap<>();

    @Override
    public Long saveSample(Sample sample) {
        if (checkSampleExists(sample)) {
            throw new SampleExistsException(String.format("Sample with genotype: %s for Strain : %s already exists", sample.getGenotype(), sample.getStrainOrBreed()));
        } else {
            Long id = (long) nextSampleId();
            var sampleToSave = new Sample(
                    id,
                    sample.getSampleName(),
                    sample.getDisease(),
                    sample.getStrainOrBreed(),
                    sample.getGenotype()
            );
            samples.put(id, sampleToSave);
            return id;
        }
    }

    @Override
    public Sample updateSample(Sample sample) {
        var sample1 = samples.get(sample.getId());
        if (sample1 == null) {
            throw new SampleNotFoundException(String.format("Sample for id %n not found", sample.getId()));
        } else {
            samples.put(sample.getId(), sample);
            return sample;
        }
    }

    @Override
    public Optional<Sample> getSampleById(Long sampleId) {
        var sample1 = samples.get(sampleId);
        if (sample1 == null) {
            throw new SampleNotFoundException(String.format("Sample for id %n not found", sampleId));
        } else {
            return Optional.of(samples.get(sampleId));
        }
    }

    @Override
    public Optional<List<Sample>> getAllSamples() {
        return Optional.of(new ArrayList<>(samples.values()));
    }

    private boolean checkSampleExists(Sample sample) {
        return samples.values().stream().anyMatch(c -> c.isSameSample(sample));
    }

    private int nextSampleId() {
        return samples.size() + 1;
    }

}
