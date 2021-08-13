package com.labfolder.labexperimentinventory.samples.store;

import com.labfolder.labexperimentinventory.exceptions.SampleExistsException;
import com.labfolder.labexperimentinventory.exceptions.SampleNotFoundException;
import com.labfolder.labexperimentinventory.samples.models.Sample;
import com.labfolder.labexperimentinventory.samples.store.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SqlSampleStore implements SampleStore {

    @Autowired
    private SampleRepository sampleRepository;

    @Override
    public Long saveSample(Sample sample) {
        if (checkSampleExists(sample)) {
            throw new SampleExistsException(String.format("Sample with genotype: %s for Strain : %s already exists", sample.getGenotype(), sample.getStrainOrBreed()));
        }
        return sampleRepository.save(sample).getId();
    }

    @Override
    public Sample updateSample(Sample sample) {
        var sample1 = sampleRepository.findById(sample.getId());
        if (sample1.isPresent()) {
            sampleRepository.save(sample);
            return sample;
        }
        throw new SampleNotFoundException(String.format("Sample for id %n not found", sample.getId()));
    }

    @Override
    public Optional<Sample> getSampleById(Long sampleId) {
        var sample1 = sampleRepository.findById(sampleId);
        if (sample1.isPresent()) {
            return sampleRepository.findById(sampleId);
        }
        throw new SampleNotFoundException(String.format("Sample for id %n not found", sampleId));
    }

    @Override
    public Optional<List<Sample>> getAllSamples() {
        return Optional.of(sampleRepository.findAll());
    }

    private boolean checkSampleExists(Sample sample) {
        return sampleRepository.findAll().stream().anyMatch(c -> c.isSameSample(sample));
    }
}
