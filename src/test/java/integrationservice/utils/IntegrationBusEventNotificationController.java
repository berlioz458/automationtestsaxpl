package integrationservice.utils;

import helpers.ListInfo;
import integrationservice.model.BusEventNotification;
import integrationservice.model.Company;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationBusEventNotificationApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationBusEventNotificationController {
    public static File BusEventNotificationListSchema= new File("src/test/java/integrationservice/schemas/BusEventNotificationList.json");
    @Step("Получение списка нотификаций")
    public static ListInfo<BusEventNotification> getBusEventNotificationList(Integer postBox){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request(postBox))
                .when()
                .get("v2/entity/Postbox/"+postBox+"/BusEventNotification")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(BusEventNotificationListSchema))
                .extract().as(new TypeRef<ListInfo<BusEventNotification>>() {
                });
    }
    @Step("Получение нотификаций по параметру")
    public static ListInfo<BusEventNotification> getBusEventNotificationByParams(Integer postBox,String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request(postBox))
                .param(params,value)
                .when()
                .get("v2/entity/Postbox/"+postBox+"/BusEventNotification")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(BusEventNotificationListSchema))
                .extract().as(new TypeRef<ListInfo<BusEventNotification>>() {
                });
    }
}
