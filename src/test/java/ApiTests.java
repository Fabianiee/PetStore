import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {
    @BeforeAll
    public static void setup () {
        RestAssured.baseURI = "https://petstore.swagger.io";
    }
    @Test
    public void testPostNewPet() {
        String petJson = "{ \"id\": 0, \"name\": \"doggie\", \"status\": \"available\" }";

        given()
                .contentType("application/json")
                .body(petJson)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("doggie"));
    }
    @Test
    public void testGetPetById() {
        given()
                .pathParam("petId", 1)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
    @Test
    public void testPutPet() {
        String updatedPetJson = "{ \"id\": 1, \"name\": \"doggie\", \"status\": \"sold\" }";

        given()
                .contentType("application/json")
                .body(updatedPetJson)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("status", equalTo("sold"));
    }
    @Test
    public void testDeletePet() {
        given()
                .pathParam("petId", 1)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }
}
