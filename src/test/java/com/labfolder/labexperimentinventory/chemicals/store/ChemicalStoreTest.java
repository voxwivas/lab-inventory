package com.labfolder.labexperimentinventory.chemicals.store;

import com.labfolder.labexperimentinventory.LabExperimentInventoryApplication;
import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import com.labfolder.labexperimentinventory.exceptions.ChemicalExistsException;
import com.labfolder.labexperimentinventory.exceptions.ChemicalNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = {"integration-test"})
@SpringBootTest(classes = LabExperimentInventoryApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ChemicalStoreTest {

    private final List<ChemicalStore> chemicalStores = new ArrayList<>();

    @Autowired
    private ChemicalStore sqlStore;

    @BeforeEach
    public void setUp() {
        chemicalStores.add(new FakeChemicalStore());
        chemicalStores.add(sqlStore);
    }

    @AfterEach
    public void cleanUp() {
        chemicalStores.clear();
    }

    @Test
    public void when_a_request_is_made_to_save_a_chemical_it_is_successful() {
        runWith(store -> {
            var chemical1 = generateFakeChemical("Chemical 1");
            var id = store.saveChemical(chemical1);
            assertNotNull(id);
        });
    }

    @Test
    public void when_a_request_is_made_to_add_an_already_existing_chemical_it_should_not_succeed() {
        runWith(store -> {
            var chemical1 = generateFakeChemical("Chemical 1");
            var id = store.saveChemical(chemical1);
            assertThrows(ChemicalExistsException.class, () -> store.saveChemical(chemical1));
        });
    }

    @Test
    public void when_a_request_is_made_to_retreive_a_chemical_given_the_id_is_successful() {
        runWith(store -> {
            var chemical1 = generateFakeChemical("Chemical 1");
            var id = store.saveChemical(chemical1);
            var retreivedChemical = store.getChemicalById(id);

            assertNotNull(retreivedChemical);
            assertEquals(chemical1.getChemicalName(), retreivedChemical.get().getChemicalName());
            assertEquals(chemical1.getAcidity(), retreivedChemical.get().getAcidity());
            assertEquals(chemical1.getFlammability(), retreivedChemical.get().getFlammability());
//            assertEquals(chemical1.getReactivity(), retreivedChemical.get().getReactivity());
            assertEquals(chemical1.getToxicity(), retreivedChemical.get().getToxicity());
        });
    }

    @Test
    public void when_a_request_is_made_to_get_a_chemical_given_a_non_existing_id_is_not_successful() {
        runWith(store -> assertThrows(ChemicalNotFoundException.class, () -> store.getChemicalById(4L)));
    }

    @Test
    public void when_a_request_is_made_to_update_a_chemical_given_the_id_it_is_successful() {
        runWith(store -> {
            var chemical1 = generateFakeChemical("Chemical 1");
            var id = store.saveChemical(chemical1);
            var retreivedChemical = store.getChemicalById(id);

            assertNotNull(retreivedChemical);
            assertEquals(chemical1.getChemicalName(), retreivedChemical.get().getChemicalName());
            assertEquals(chemical1.getAcidity(), retreivedChemical.get().getAcidity());
            assertEquals(chemical1.getFlammability(), retreivedChemical.get().getFlammability());
//            assertEquals(chemical1.getReactivity(), retreivedChemical.get().getReactivity());
            assertEquals(chemical1.getToxicity(), retreivedChemical.get().getToxicity());

            Chemical chemicalToUpdate = retreivedChemical.get();
            chemicalToUpdate.updateAcidity(78);
            chemicalToUpdate.updateFlammability(9);

            store.updateChemical(chemicalToUpdate);
            var updatedChemical = store.getChemicalById(id);

            assertNotNull(retreivedChemical);
            assertEquals(78, updatedChemical.get().getAcidity());
            assertEquals(9, updatedChemical.get().getFlammability());
        });
    }

    @Test
    public void when_a_request_is_made_to_list_all_chemicals_it_is_successful() {
        runWith(store -> {
            store.saveChemical(generateFakeChemical(""));
            store.saveChemical(generateFakeChemical(""));
            store.saveChemical(generateFakeChemical(""));
            store.saveChemical(generateFakeChemical(""));
            store.saveChemical(generateFakeChemical(""));

            var allChemicals = store.getAllChemicals();

            assertNotNull(allChemicals);
            assertEquals(5,allChemicals.get().size());
        });
    }

    private Chemical generateFakeChemical(String name) {

        String chemicalName = !name.isEmpty() ? name : UUID.randomUUID() + Instant.now().toString();

        return new Chemical(
                chemicalName,
                20,
                34,
                12,
                Collections.emptyList()
        );
    }

    private void runWith(Consumer<ChemicalStore> store) {
        chemicalStores.forEach(store);
    }

}