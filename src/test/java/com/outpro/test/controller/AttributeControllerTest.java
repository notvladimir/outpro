package com.outpro.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AttributeControllerTest {

    @LocalServerPort
    private int randomServerPort;

    @Test
    void shouldReturnAllAttributes() {
        with()
                .port(randomServerPort)
                .get("/api/v1/attributes")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("size()", is(18))
                .body("[0].code", equalTo("supplierpacksize"))
                .body("[0].labels.size()", is(17))
                .body("[0].labels[0].locale", equalTo("it_IT"))
                .body("[0].labels[0].value", equalTo("Numero in confezioni dal fornitore"));
    }
}
