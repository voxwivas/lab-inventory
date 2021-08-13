package com.labfolder.labexperimentinventory.samples.store;

import com.labfolder.labexperimentinventory.LabExperimentInventoryApplication;
import com.labfolder.labexperimentinventory.exceptions.SampleExistsException;
import com.labfolder.labexperimentinventory.exceptions.SampleNotFoundException;
import com.labfolder.labexperimentinventory.samples.models.Sample;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = {"integration-test"})
@SpringBootTest(classes = LabExperimentInventoryApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SampleStoreTest {

    private final List<SampleStore> sampleStores = new ArrayList<>();

    @Autowired
    private SampleStore sqlStore;

    @BeforeEach
    public void setUp() {
        sampleStores.add(new FakeSampleStore());
        sampleStores.add(sqlStore);
    }

    @AfterEach
    public void cleanUp() {
        sampleStores.clear();
    }

    @Test
    public void when_a_request_is_made_to_save_a_sample_it_is_successful() {
        runWith(store -> {
            var sample1 = generateFakeSample("Sample 1");
            var id = store.saveSample(sample1);
            assertNotNull(id);
        });
    }

    @Test
    public void when_a_request_is_made_to_add_an_already_existing_sample_it_should_not_succeed() {
        runWith(store -> {
            var sample1 = generateFakeSample("Sample 1");
            var id = store.saveSample(sample1);
            assertThrows(SampleExistsException.class, () -> store.saveSample(sample1));
        });
    }

    @Test
    public void when_a_request_is_made_to_retreive_a_sample_given_the_id_is_successful() {
        runWith(store -> {
            var sample1 = generateFakeSample("Sample 1");
            var id = store.saveSample(sample1);
            var retreivedSample = store.getSampleById(id);

            assertNotNull(retreivedSample);
            assertEquals(sample1.getSampleName(), retreivedSample.get().getSampleName());
            assertEquals(sample1.getDisease(), retreivedSample.get().getDisease());
            assertEquals(sample1.getGenotype(), retreivedSample.get().getGenotype());
            assertEquals(sample1.getStrainOrBreed(), retreivedSample.get().getStrainOrBreed());

        });
    }

    @Test
    public void when_a_request_is_made_to_get_a_sample_given_a_non_existing_id_is_not_successful() {
        runWith(store -> assertThrows(SampleNotFoundException.class, () -> store.getSampleById(3L)));
    }

    @Test
    public void when_a_request_is_made_to_update_a_sample_given_the_id_it_is_successful() {
        runWith(store -> {
            var sample1 = generateFakeSample("Sample 1");
            var id = store.saveSample(sample1);
            var retreivedSample = store.getSampleById(id);

            assertNotNull(retreivedSample);
            assertEquals(sample1.getSampleName(), retreivedSample.get().getSampleName());
            assertEquals(sample1.getDisease(), retreivedSample.get().getDisease());
            assertEquals(sample1.getGenotype(), retreivedSample.get().getGenotype());
            assertEquals(sample1.getStrainOrBreed(), retreivedSample.get().getStrainOrBreed());

            Sample sampleToUpdate = retreivedSample.get();
            sampleToUpdate.updateStrainOrBreed("Indian");
            sampleToUpdate.updateDisease("COVID 19");

            store.updateSample(sampleToUpdate);
            var updatedChemical = store.getSampleById(id);

            assertNotNull(retreivedSample);
            assertEquals("Indian", updatedChemical.get().getStrainOrBreed());
            assertEquals("COVID 19", updatedChemical.get().getDisease());
        });
    }

    @Test
    public void when_a_request_is_made_to_list_all_samples_it_is_successful() {
        runWith(store -> {
            store.saveSample(generateFakeSample(""));
            store.saveSample(generateFakeSample(""));
            store.saveSample(generateFakeSample(""));
            store.saveSample(generateFakeSample(""));
            store.saveSample(generateFakeSample(""));

            var allChemicals = store.getAllSamples();

            assertNotNull(allChemicals);
            assertEquals(5, allChemicals.get().size());
        });
    }

    private Sample generateFakeSample(String name) {

        String sampleName = !name.isEmpty() ? name : UUID.randomUUID() + Instant.now().toString();

        return new Sample(
                sampleName,
                "malaria",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    private void runWith(Consumer<SampleStore> store) {
        sampleStores.forEach(store);
    }
}