package bus.clientservice.core.utils;

import io.qameta.allure.Step;

import static bus.clientservice.core.spec.ClientServiceApiSpec.*;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class TokensController {

    @Step("Получение токена из письма")
    public static String getTokenByEmail(String email) {
        return given()
                .filter(withCustomTemplate())
                .spec(service_request)
                .get("/listEmailMessage?recipientAddress=" + email + "&limit=1&sort%5Bid%5D=desc")
                .then()
                .spec(response)
                .extract().jsonPath().get("data[0].parameters.TOKEN");
    }

    @Step("Получение токена из смс")
    public static String getTokenBySms(String phone) {
        return given()
                .filter(withCustomTemplate())
                .spec(service_request)
                .get("/listSmsMessage?recipientAddress=" + phone + "&limit=1&sort%5Bid%5D=desc")
                .then()
                .spec(response)
                .extract().jsonPath().get("data[0].parameters.TOKEN");
    }

    @Step("Получение токена из push")
    public static String getTokenByPush(String deviceToken) {
        return given()
                .filter(withCustomTemplate())
                .spec(service_request)
                .get("/listPushMessage?recipientAddress=" + deviceToken + "&limit=1&sort%5Bid%5D=desc")
                .then()
                .spec(response)
                .extract().jsonPath().get("data[0].parameters.TOKEN");
    }
}
