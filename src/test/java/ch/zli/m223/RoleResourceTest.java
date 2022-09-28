package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class RoleResourceTest {

    @Test
    public void testGetRoles() {
        given()
          .when()
            .get("http://localhost:8080/roles")
          .then()
            .statusCode(200)
            .body(
                is("[{\"id\":1,\"title\":\"Mitglied\"},{\"id\":2,\"title\":\"Administrator\"}]")
            );
    }
}