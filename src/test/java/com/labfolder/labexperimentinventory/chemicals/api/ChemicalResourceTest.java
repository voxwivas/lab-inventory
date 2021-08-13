package com.labfolder.labexperimentinventory.chemicals.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.http.ContentType;
import com.labfolder.labexperimentinventory.chemicals.models.Chemical;
import com.labfolder.labexperimentinventory.chemicals.models.CreateChemicalRequest;
import com.labfolder.labexperimentinventory.util.ResourceTest;
import com.labfolder.labexperimentinventory.utils.wrappers.Id;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

class ChemicalResourceTest extends ResourceTest {

    @Test
    public void when_a_request_is_received_to_create_a_chemical_it_is_successful() throws JsonProcessingException {
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical("Chemical 1"))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(1, id.getId());
    }

    @Test
    public void when_a_request_is_received_to_get_a_chemical_by_id_it_is_successful() throws JsonProcessingException {
        var createChemicalRequest = generateFakeChemical("Chemical 1");
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(createChemicalRequest)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        var resp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("chemicalId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(ChemicalResource.CHEMICAL_RESOURCE_PATH + "/{chemicalId}");
        var chemical = mapper.readValue(resp.prettyPrint(), Chemical.class);
        Assertions.assertEquals(createChemicalRequest.getChemicalName(), chemical.getChemicalName());
        Assertions.assertEquals(createChemicalRequest.getFlammability(), chemical.getFlammability());
        Assertions.assertEquals(createChemicalRequest.getToxicity(), chemical.getToxicity());
        Assertions.assertEquals(createChemicalRequest.getAcidity(), chemical.getAcidity());
    }

    @Test
    public void when_a_request_is_received_to_update_a_chemical_it_is_successful() throws JsonProcessingException {
        var createChemicalRequest = generateFakeChemical("Chemical 1");
        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(createChemicalRequest)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        var id = mapper.readValue(response.prettyPrint(), Id.class);
        var resp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("chemicalId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(ChemicalResource.CHEMICAL_RESOURCE_PATH + "/{chemicalId}");
        var chemical = mapper.readValue(resp.prettyPrint(), Chemical.class);

        Assertions.assertEquals(createChemicalRequest.getChemicalName(), chemical.getChemicalName());
        Assertions.assertEquals(createChemicalRequest.getFlammability(), chemical.getFlammability());

        var chemUpdate = new CreateChemicalRequest(
                chemical.getId(),
                chemical.getChemicalName(),
                50,
                60,
                90,
                chemical.getReactivity()
        );

        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(chemUpdate)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .put(ChemicalResource.CHEMICAL_RESOURCE_PATH + "/update");

        var updateResp = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .pathParam("chemicalId", id.getId())
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(ChemicalResource.CHEMICAL_RESOURCE_PATH + "/{chemicalId}");
        var updatedChemical = mapper.readValue(updateResp.prettyPrint(), Chemical.class);

        Assertions.assertEquals(createChemicalRequest.getChemicalName(), updatedChemical.getChemicalName());
        Assertions.assertEquals(50, updatedChemical.getFlammability());
        Assertions.assertEquals(60, updatedChemical.getToxicity());
        Assertions.assertEquals(90, updatedChemical.getAcidity());
    }

    @Test
    public void when_a_request_is_received_to_list_all_chemicals_it_is_successful() {
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .body(generateFakeChemical(""))
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .post(ChemicalResource.CHEMICAL_RESOURCE_PATH);

        var response = given()
                .log()
                .everything()
                .header("Content-Type", ContentType.JSON)
                .expect()
                .statusCode(HttpURLConnection.HTTP_OK)
                .when()
                .get(ChemicalResource.CHEMICAL_RESOURCE_PATH);
        response.prettyPrint();

        var chemicals = fromJSON(mapper, new TypeReference<List<Chemical>>() {
        }, response.print());
        Assertions.assertNotNull(chemicals);
        Assertions.assertEquals(7, chemicals.size());
    }

    private CreateChemicalRequest generateFakeChemical(String name) {
        String chemicalName = !name.isEmpty() ? name : UUID.randomUUID() + Instant.now().toString();
        return new CreateChemicalRequest(
                chemicalName,
                20,
                34,
                12,
                Collections.emptyList()
        );
    }

}