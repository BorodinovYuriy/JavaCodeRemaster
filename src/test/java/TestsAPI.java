import io.restassured.RestAssured;
import org.example.helpers.Validators;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestsAPI extends BaseApiTest {

    @Test
    public void positiveRegistrationCheck() {
        RestAssured.given()
                .spec(getRequestSpec())
                .body(createValidRegistrationData())
                .when()
                .post("/register")
                .then()
                .spec(getResponseSpec())
                .log().body()
                .statusCode(200);
    }

    @Test
    public void negativeRegistrationCheck(){
        RestAssured.given()
                .spec(getRequestSpec())
                .body(createInvalidRegistrationData())
                .when()
                .post("/register")
                .then()
                .spec(getResponseSpec())
                .log().body()
                .statusCode(400);
    }

    @Test
    public void usersEmailEndCheck(){
        RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get("/users?page=2")
                .then()
                .spec(getResponseSpec())
                .log().body()
                .body("data.email",not(hasItem(nullValue())))
                .body("data.email", everyItem(endsWith("@reqres.in")))
                .statusCode(200);
    }

    @Test
    public void userDeleteCheck(){
        RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void userPatchDateCheck() {
        String updatedAt = RestAssured.given()
                .spec(getRequestSpec())
                .body(createPatchUserData())
                .when()
                .patch("/users/2")
                .then()
                .spec(getResponseSpec())
                .statusCode(200)
                .extract()
                .path("updatedAt");

        System.out.println("Задержка ответа при UPDATE: "+ Validators.parseAndReturnTimeDiff(updatedAt)+" sec");
        assertThat(Validators.parseAndReturnTimeDiff(updatedAt), lessThanOrEqualTo(5L));
    }
}















