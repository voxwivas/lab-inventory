package com.labfolder.labexperimentinventory.devices.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.http.ContentType;
import com.labfolder.labexperimentinventory.devices.models.CreateDeviceRequest;
import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.util.ResourceTest;
import com.labfolder.labexperimentinventory.utils.wrappers.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

class DeviceResourceTest extends ResourceTest {

    @Test
    public void when_a_request_is_received_to_create_a_device_it_is_successful() throws JsonProcessingException {
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice("Device 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(1, id.getId());
    }

    @Test
    public void when_a_request_is_received_to_get_a_device_by_id_it_is_successful() throws JsonProcessingException {
        var createDeviceRequest = generateFakeCreateDevice("Chemical 1");
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(createDeviceRequest)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);

        var resp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("deviceId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(DeviceResource.DEVICE_RESOURCE_PATH + "/{deviceId}");
        var device = mapper.readValue(resp.prettyPrint(), Device.class);
        Assertions.assertEquals(createDeviceRequest.getElement(), device.getElement());
        Assertions.assertEquals(createDeviceRequest.getDiameterInMeters(), device.getDiameterInMeters());
        Assertions.assertEquals(createDeviceRequest.getLengthInMeters(), device.getLengthInMeters());
        Assertions.assertEquals(createDeviceRequest.getMassInKg(), device.getMassInKg());
        Assertions.assertEquals(createDeviceRequest.getRemarks(), device.getRemarks());
    }

    @Test
    public void when_a_request_is_received_to_update_a_device_it_is_successful() throws JsonProcessingException {
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice("Device 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        var resp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("deviceId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(DeviceResource.DEVICE_RESOURCE_PATH + "/{deviceId}");
        var device = mapper.readValue(resp.prettyPrint(), Device.class);
        var deviceUpdate = new CreateDeviceRequest(
                device.getId(),
                device.getElement(),
                device.getMassInKg(),
                device.getLengthInMeters(),
                device.getDiameterInMeters(),
                "Updated remarks value"
        );
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(deviceUpdate)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .put(DeviceResource.DEVICE_RESOURCE_PATH);
        var updateResp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("deviceId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(DeviceResource.DEVICE_RESOURCE_PATH + "/{deviceId}");
        var updatedDevice = mapper.readValue(updateResp.prettyPrint(), Device.class);
        Assertions.assertEquals(device.getElement(), updatedDevice.getElement());
        Assertions.assertEquals(device.getDiameterInMeters(), updatedDevice.getDiameterInMeters());
        Assertions.assertEquals(device.getLengthInMeters(), updatedDevice.getLengthInMeters());
        Assertions.assertEquals(device.getMassInKg(), updatedDevice.getMassInKg());
        Assertions.assertNotEquals(device.getRemarks(), updatedDevice.getRemarks());
        Assertions.assertEquals("Updated remarks value", updatedDevice.getRemarks());
    }

    @Test
    public void when_a_request_is_received_to_list_all_devices_it_is_successful() {
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice("Device 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice("Device 2 "))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice(" Device 3"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice("Device 4 "))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeCreateDevice("Device 5 "))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(DeviceResource.DEVICE_RESOURCE_PATH);
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(DeviceResource.DEVICE_RESOURCE_PATH);

        var devices = fromJSON(mapper, new TypeReference<List<Device>>() {
        }, response.print());
        Assertions.assertNotNull(devices);
        Assertions.assertEquals(5, devices.size());
    }

    private CreateDeviceRequest generateFakeCreateDevice(String name) {
        String deviceName = !name.isEmpty() ? name : UUID.randomUUID() + Instant.now().toString();

        return new CreateDeviceRequest(
                deviceName,
                20,
                34,
                12,
                UUID.randomUUID().toString()
        );
    }


}