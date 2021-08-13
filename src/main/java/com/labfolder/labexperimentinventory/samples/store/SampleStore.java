package com.labfolder.labexperimentinventory.samples.store;


import com.labfolder.labexperimentinventory.samples.models.Sample;

import java.util.List;
import java.util.Optional;

public interface SampleStore {

    Long saveSample(Sample sample);

    Sample updateSample(Sample sample);

    Optional<Sample> getSampleById(Long sampleId);

    Optional<List<Sample>> getAllSamples();
}
