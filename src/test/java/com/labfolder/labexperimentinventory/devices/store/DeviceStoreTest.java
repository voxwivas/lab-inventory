package com.labfolder.labexperimentinventory.devices.store;

import com.labfolder.labexperimentinventory.LabExperimentInventoryApplication;
import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.exceptions.DeviceExistsException;
import com.labfolder.labexperimentinventory.exceptions.DeviceNotFoundException;
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
class DeviceStoreTest {

    private final List<DeviceStore> deviceStores = new ArrayList<>();
    @Autowired
    private DeviceStore sqlStore;

    @BeforeEach
    public void setUp() {
        deviceStores.add(new FakeDeviceStore());
        deviceStores.add(sqlStore);
    }

    @AfterEach
    public void cleanUp() {
        deviceStores.clear();
    }

    @Test
    public void when_a_request_is_made_to_save_a_device_it_is_successful() {
        runWith(store -> {
            var device1 = generateFakeDevice("Device 1");
            var id = store.saveDevice(device1);
            assertNotNull(id);
        });
    }

    @Test
    public void when_a_request_is_made_to_add_an_already_existing_device_it_should_not_succeed() {
        runWith(store -> {
            var device1 = generateFakeDevice("Device 1");
            var id = store.saveDevice(device1);
            assertThrows(DeviceExistsException.class, () -> store.saveDevice(device1));
        });
    }

    @Test
    public void when_a_request_is_made_to_retreive_a_device_given_the_id_is_successful() {
        runWith(store -> {
            var device1 = generateFakeDevice("Device 1");
            var id = store.saveDevice(device1);
            var retreivedDevice = store.getDeviceById(id);

            assertNotNull(retreivedDevice);
            assertEquals(device1.getElement(), retreivedDevice.get().getElement());
            assertEquals(device1.getDiameterInMeters(), retreivedDevice.get().getDiameterInMeters());
            assertEquals(device1.getLengthInMeters(), retreivedDevice.get().getLengthInMeters());
            assertEquals(device1.getRemarks(), retreivedDevice.get().getRemarks());
        });
    }

    @Test
    public void when_a_request_is_made_to_get_a_device_given_a_non_existing_id_is_not_successful() {
        runWith(store -> assertThrows(DeviceNotFoundException.class, () -> store.getDeviceById(4L)));
    }

    @Test
    public void when_a_request_is_made_to_update_a_device_given_the_id_it_is_successful() {
        runWith(store -> {
            var device1 = generateFakeDevice("Device 1");
            var id = store.saveDevice(device1);
            var retreivedDevice = store.getDeviceById(id);

            assertNotNull(retreivedDevice);
            assertEquals(device1.getElement(), retreivedDevice.get().getElement());
            assertEquals(device1.getDiameterInMeters(), retreivedDevice.get().getDiameterInMeters());
            assertEquals(device1.getLengthInMeters(), retreivedDevice.get().getLengthInMeters());
            assertEquals(device1.getRemarks(), retreivedDevice.get().getRemarks());

            Device deviceToUpdate = retreivedDevice.get();
            deviceToUpdate.updateRemarks("it is a good device");

            store.updateDevice(deviceToUpdate);
            var updatedChemical = store.getDeviceById(id);

            assertNotNull(retreivedDevice);
            assertEquals("it is a good device", updatedChemical.get().getRemarks());
        });
    }

    @Test
    public void when_a_request_is_made_to_list_all_devices_it_is_successful() {
        runWith(store -> {
            store.saveDevice(generateFakeDevice(""));
            store.saveDevice(generateFakeDevice(""));
            store.saveDevice(generateFakeDevice(""));
            store.saveDevice(generateFakeDevice(""));
            store.saveDevice(generateFakeDevice(""));

            var allChemicals = store.getAllDevices();

            assertNotNull(allChemicals);
            assertEquals(5, allChemicals.get().size());
        });
    }

    private Device generateFakeDevice(String name) {

        String deviceName = !name.isEmpty() ? name : UUID.randomUUID() + Instant.now().toString();

        return new Device(
                deviceName,
                20,
                34,
                12,
                UUID.randomUUID().toString()
        );
    }

    private void runWith(Consumer<DeviceStore> store) {
        deviceStores.forEach(store);
    }
}