package com.labfolder.labexperimentinventory.samples.service;

import com.labfolder.labexperimentinventory.samples.models.CreateSampleRequest;
import com.labfolder.labexperimentinventory.samples.models.Sample;
import com.labfolder.labexperimentinventory.samples.store.SampleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    @Autowired
    private SampleStore sampleStore;

    public Long saveSample(CreateSampleRequest createSampleRequest) {
        return sampleStore.saveSample(createSampleRequest.asSample());
    }

    public Optional<Sample> getSampleById(Long sampleId) {
        return sampleStore.getSampleById(sampleId);
    }

    public Sample updateSample(CreateSampleRequest createSampleRequest) {
        return sampleStore.updateSample(createSampleRequest.asSampleUpdate());
    }

    public Optional<List<Sample>> getAllSamples() {
        return sampleStore.getAllSamples();
    }
}
