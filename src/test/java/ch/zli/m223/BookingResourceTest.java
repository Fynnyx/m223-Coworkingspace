package ch.zli.m223;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class BookingResourceTest {

    String adminToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL3psaS5jaC9pc3N1ZXIiLCJ1cG4iOiJhZG1pbkBhZG1pbi5jaCIsImdyb3VwcyI6WyJBZG1pbmlzdHJhdG9yIl0sInJvbGUiOiJBZG1pbmlzdHJhdG9yIiwiZmlyc3RuYW1lIjoiYWRtaW4iLCJsYXN0bmFtZSI6ImFkbWluIiwiaWQiOjIsImlhdCI6MTY2NDM2Nzg4NSwiZXhwIjozODExODUxNTMyLCJqdGkiOiJlMmQ4NWJlMS01MWFkLTRmMTktODM5OC1lZTIzZmViMDk0NzMifQ.dY44LUFYbzFeVuua-SbEHIduMBp3DN24N1JXQr4gi-D90AcOO_RONCXvmUTui2R7HeGisJKyTnc18OQtqutROlSbO0eEMczsJKDE5DHho2XN8AlB8SMvrIgi0Pb611qJljqb-LgYamiA-UgghRPS8A2KSk2pHnRR-736C9yemGsGtOuZ3qfLd_5Esu4TwXMEc0FpVDS7HCmpZNXvAKMWiu3eWuawAvOPQpeuLE-LN_jxTQVX0ppwPmCORgSdYZCZ1aOaszFOGNLQISlRUUVrAY6M5jjqUPP1p6GoHfuLvmfHaPRkxx2ZCcy-jig6NdzlaqtGNGA9AiWpLfqnK_9AqQ";
    String memberToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL3psaS5jaC9pc3N1ZXIiLCJ1cG4iOiJ1c2VyQHVzZXIuY2giLCJncm91cHMiOlsiTWl0Z2xpZWQiXSwicm9sZSI6Ik1pdGdsaWVkIiwiZmlyc3RuYW1lIjoidXNlciIsImxhc3RuYW1lIjoidXNlciIsImlkIjoxLCJpYXQiOjE2NjQzNjgwNDgsImV4cCI6MzgxMTg1MTY5NSwianRpIjoiYjQ5ZmIzYzUtODU0Mi00YjQ5LWJlMTktMWQ4YzZmYWI1ODYyIn0.iS6HJZDpxVqwEjM4SFZ9nFISO6j-8TdjgP0M7bpfteuBH31xNreYvV0kWtvNqG3FFykQ_90x-8scaJ_goPkxpu5LkJQMwPp8Zw9M60vWWrcG_2shSSjmaJ_GKTPCJvQ_Hy-iKB7drRV3eIZGUEo0S6k3Gy_WX6kZb-TtqmIhCyfrphsuc_IDRXTtr9XVV4bjf6xwbjpaI6d5PyvU6Uorxld72YJOrySWi_GuHP__2mTT2IR4CWUAXW-cwCPBAiZZ9cMGAddEYRfzEQrv2LoLIqjWoTbDr74zNmoAswXrSXUNiZCXiafAYdBDc5xesD1Dqui_zbDzMuXg2QqD7Wdp0w";

    @Test
    public void testGetBookingOwnAccess() {
        given()
        .header("Authorization", "Bearer " + memberToken)
        .body("{\"startTime\":\"2022-03-10T12:15:50\",\"endTime\":\"2022-03-10T12:15:50\",\"aprooved\":false,\"user\":{\"id\":1},\"place\":{\"id\":1}}")
        .contentType(ContentType.JSON)
          .when()
            .put("http://localhost:8080/bookings/2")
          .then()
            .statusCode(200);
    }

    @Test
    public void testGetBookingOwnAccessNegative() {
        given()
        .header("Authorization", "Bearer kjashdlkajshdklajshdlkajshdkljahdsklajgdsahgdsjkahgsdkjaksdhalkjsdhakljhdklajhdklajhdlkjasdhlkjashdklasjhdkljashdkljasdh")
        .contentType(ContentType.JSON)
          .when()
            .get("http://localhost:8080/bookings/2")
          .then()
            .statusCode(401);
    }

    // Admin Access to Bookings

    @Test
    public void testBookingGetAdminAccess() {
        given()
            .header("Authorization", "Bearer " + adminToken)
            .when()
                .get("http://localhost:8080/bookings")
            .then()
                .statusCode(200);
    }

    @Test
    public void testBookingUpdateAdminAccess() {
        given()
        .header("Authorization", "Bearer " + adminToken)
        .body("{\"startTime\":\"2022-03-10T12:15:50\",\"endTime\":\"2022-03-10T12:15:50\",\"aprooved\":false,\"user\":{\"id\":1},\"place\":{\"id\":2}}")
        .contentType(ContentType.JSON)
          .when()
            .put("http://localhost:8080/bookings/2")
          .then()
            .statusCode(200);
    }
}