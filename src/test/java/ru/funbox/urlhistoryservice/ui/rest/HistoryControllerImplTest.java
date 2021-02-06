package ru.funbox.urlhistoryservice.ui.rest;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class HistoryControllerImplTest {

    @Test
    void saveVisitedLinks_ShouldReturnTrue_WhenSendUrlsAsJSONToVisitedLinkEndpoint() {
        String testJSONString = "{\n" +
                "  \"links\": [\n" +
                "  \"https://ya.ru\",\n" +
                "  \"https://ya.ru?q=123\",\n" +
                "  \"funbox.ru\",\n" +
                "  \"https://stackoverflow.com/questions/11828270/how-to-exit-the-vim-editor\"\n" +
                "  ]\n" +
                "}\n";
        given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(testJSONString)
                .post("/visited_links")
                .then()
                .statusCode(Status.OK.getStatusCode())
                .body("status", is("ok"));
    }

    @Test
    void getVisitedDomainsBetweenTime_ShouldReturnTrue_WhenSendRequestToDomainsEndpointWithCorrectTimeRange() {
        given()
                .when()
                .queryParam("from", 0)
                .queryParam("to", System.currentTimeMillis())
                .get(" /visited_domains")
                .then()
                .statusCode(Status.OK.getStatusCode())
                .body("status", is("ok"));
    }
}
