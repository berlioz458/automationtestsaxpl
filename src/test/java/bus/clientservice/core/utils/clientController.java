package bus.clientservice.core.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static bus.clientservice.core.spec.ClientServiceApiSpec.request;
import static bus.clientservice.core.spec.ClientServiceApiSpec.response;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;

public class clientController {

    @Step("Получение токена из письма")
    public static String getTokenByEmail(String email) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/listEmailMessage?recipientAddress=" + email + "&limit=1&sort%5Bid%5D=desc")
                .then()
                .spec(response)
                .extract().jsonPath().get("data[0].parameters.TOKEN");
    }

    @Step("Получение токена из смс")
    public static String getTokenBySms(String phone) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/listSmsMessage?recipientAddress=" + phone + "&limit=1&sort%5Bid%5D=desc")
                .then()
                .spec(response)
                .extract().jsonPath().get("data[0].parameters.TOKEN");
    }

    @Step("Получение токена из push")
    public static String getTokenByPush(String deviceToken) {
        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .get("/listPushMessage?recipientAddress=" + deviceToken + "&limit=1&sort%5Bid%5D=desc")
                .then()
                .spec(response)
                .extract().jsonPath().get("data[0].parameters.TOKEN");
    }
}
