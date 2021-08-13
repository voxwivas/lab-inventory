package com.labfolder.labexperimentinventory.samples.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.http.ContentType;
import com.labfolder.labexperimentinventory.devices.models.Device;
import com.labfolder.labexperimentinventory.samples.models.CreateSampleRequest;
import com.labfolder.labexperimentinventory.samples.models.Sample;
import com.labfolder.labexperimentinventory.util.ResourceTest;
import com.labfolder.labexperimentinventory.utils.wrappers.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

class SampleResourceTest extends ResourceTest {

    @Test
    public void when_a_request_is_received_to_create_a_sample_it_is_successful() throws JsonProcessingException {
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeSample("Sample 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(1, id.getId());
    }

    @Test
    public void when_a_request_is_received_to_get_a_sample_by_id_it_is_successful() throws JsonProcessingException {
        var createSampleRequest = generateFakeSample("Sample 1");
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(createSampleRequest)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        var resp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("sampleId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(SampleResource.SAMPLE_RESOURCE_PATH + "/{sampleId}");
        var sample = mapper.readValue(resp.prettyPrint(), Sample.class);
        Assertions.assertEquals(createSampleRequest.getSampleName(), sample.getSampleName());
        Assertions.assertEquals(createSampleRequest.getDisease(), sample.getDisease());
        Assertions.assertEquals(createSampleRequest.getGenotype(), sample.getGenotype());
        Assertions.assertEquals(createSampleRequest.getStrainOrBreed(), sample.getStrainOrBreed());
    }

    @Test
    public void when_a_request_is_received_to_update_a_sample_it_is_successful() throws JsonProcessingException {
        var createSampleRequest = generateFakeSample("Sample 1");
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(createSampleRequest)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        var resp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("sampleId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(SampleResource.SAMPLE_RESOURCE_PATH + "/{sampleId}");
        var sample = mapper.readValue(resp.prettyPrint(), Sample.class);

        var updateSample = new CreateSampleRequest(
                sample.getId(),
                sample.getSampleName(),
                "COVID 19",
                "Indian Variant",
                sample.getGenotype()
        );

        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(updateSample)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .put(SampleResource.SAMPLE_RESOURCE_PATH);

        var updatedResp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("sampleId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(SampleResource.SAMPLE_RESOURCE_PATH + "/{sampleId}");
        var updatedSample = mapper.readValue(updatedResp.prettyPrint(), Sample.class);

        Assertions.assertEquals("COVID 19", updatedSample.getDisease());
        Assertions.assertEquals("Indian Variant", updatedSample.getStrainOrBreed());
    }

    @Test
    public void when_a_request_is_received_to_list_all_samples_it_is_successful() throws JsonProcessingException {
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeSample("Sample 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeSample("Sample 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeSample("Sample 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeSample("Sample 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(SampleResource.SAMPLE_RESOURCE_PATH);

        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(SampleResource.SAMPLE_RESOURCE_PATH);
        var devices = fromJSON(mapper, new TypeReference<List<Device>>() {
        }, response.print());
        Assertions.assertNotNull(devices);
        Assertions.assertEquals(4, devices.size());

    }

    private CreateSampleRequest generateFakeSample(String sampleName) {
        String deviceName = !sampleName.isEmpty() ? sampleName : UUID.randomUUID() + Instant.now().toString();
        return new CreateSampleRequest(
                deviceName,
                "malaria",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

}