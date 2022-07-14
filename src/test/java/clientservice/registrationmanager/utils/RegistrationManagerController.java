package clientservice.registrationmanager.utils;

import clientservice.registrationmanager.models.DeviceRegistrationRequest;
import clientservice.registrationmanager.models.DeviceToken;
import io.qameta.allure.Step;

import static clientservice.registrationmanager.spec.RegistrationManagerApiSpecs.response;
import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;
import static clientservice.registrationmanager.spec.RegistrationManagerApiSpecs.request;

public class RegistrationManagerController {
    @Step("Получить deviceToken")
    public static DeviceToken getDeviceToken(String deviceManufacturerId, String deviceUser, String userAgent, String osName, String osVersion, String deviceType) {
        DeviceRegistrationRequest body = new DeviceRegistrationRequest();
        body.setDeviceManufacturerId(deviceManufacturerId);
        body.setDeviceUser(deviceUser);
        body.setUserAgent(userAgent);
        body.setOsName(osName);
        body.setOsVersion(osVersion);
        body.setDeviceType(deviceType);

        return given()
                .filter(withCustomTemplate())
                .spec(request)
                .body(body)
                .post("/registerDevice")
                .then()
                .spec(response)
                .extract().as(DeviceToken.class);
    }
}
