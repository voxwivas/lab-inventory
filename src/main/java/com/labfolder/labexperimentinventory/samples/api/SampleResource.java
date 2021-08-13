package com.labfolder.labexperimentinventory.samples.api;

import com.labfolder.labexperimentinventory.samples.models.CreateSampleRequest;
import com.labfolder.labexperimentinventory.samples.models.Sample;
import com.labfolder.labexperimentinventory.samples.service.SampleService;
import com.labfolder.labexperimentinventory.utils.wrappers.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(SampleResource.SAMPLE_RESOURCE_PATH)
public class SampleResource {
    public static final String SAMPLE_RESOURCE_PATH = "/api/samples";

    @Autowired
    private SampleService sampleService;

    @PostMapping
    public ResponseEntity<Id> createSample(@RequestBody CreateSampleRequest createSampleRequest) {
        return ResponseEntity.ok(new Id(sampleService.saveSample(createSampleRequest)));
    }

    @GetMapping(value = "/{sampleId}")
    public ResponseEntity<Optional<Sample>> getSampleById(@PathVariable("sampleId") Long sampleId) {
        return ResponseEntity.ok(sampleService.getSampleById(sampleId));
    }

    @PutMapping
    public ResponseEntity<Sample> updateSample(@RequestBody CreateSampleRequest updateSampleRequest) {
        return ResponseEntity.ok(sampleService.updateSample(updateSampleRequest));
    }

    @GetMapping
    public ResponseEntity<Optional<List<Sample>>> getAllSamples() {
        return ResponseEntity.ok(sampleService.getAllSamples());
    }
}
