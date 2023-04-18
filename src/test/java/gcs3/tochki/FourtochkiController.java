package gcs3.tochki;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static gcs3.tochki.ApiSpecs.success_requestSpec;
import static gcs3.tochki.ApiSpecs.success_responseSpec;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class FourtochkiController {

    @Step("Получить список брендов")
    public ValidatableResponse getBrands() {
        return given()
                .filter(withCustomTemplate())
                .spec(success_requestSpec)
                .when()
                .get("vehicle/brands")
                .then()
                .spec(success_responseSpec)
                .log().body();
    }
}
