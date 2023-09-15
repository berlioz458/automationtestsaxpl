package priceaggregatorservice.utils;

import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import priceaggregatorservice.model.RoutingPoint;

import java.io.File;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;
import static priceaggregatorservice.spec.PragApiSpecs.success_request;
import static priceaggregatorservice.spec.PragApiSpecs.success_responseSpec;

public class PragRoutingPointController {
    public static File RoutingPointSchema= new File("src/test/java/priceaggregatorservice/schemas/RoutingPoint.json");
    public static File RoutingPointListSchema= new File("src/test/java/priceaggregatorservice/schemas/RoutingPointList.json");
    @Step("Получение списка точек маршрута")
    public static ListInfo<RoutingPoint> getRoutingPointList(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/RoutingPoint")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(RoutingPointListSchema))
                .extract()
                .as(new TypeRef<ListInfo<RoutingPoint>>() {
                });
    }
    @Step("Получение списка точек маршрута по парметру")
    public static ListInfo<RoutingPoint> getRoutingPointListByParams(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/RoutingPoint")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(RoutingPointListSchema))
                .extract()
                .as(new TypeRef<ListInfo<RoutingPoint>>() {
                });
    }
    @Step("Получение точки маршрута по айди")
    public static RoutingPoint getRoutingPointById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/RoutingPoint/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(RoutingPointSchema))
                .extract()
                .as(RoutingPoint.class);
    }
    @Step("Создание точки маршрута")
    public static RoutingPoint createRoutingPoint(String name, Boolean isManagedExternally, String longitude, String latitude, Boolean isTransferPoint, Ref ownerAgent, Ref inWorkSchedule, Ref outWorkSchedule){
        RoutingPoint body=new RoutingPoint();
        body.setName(name);
        body.setIsManagedExternally(isManagedExternally);
        body.setLatitude(latitude);
        body.setLongitude(longitude);
        body.setIsTransferPoint(isTransferPoint);
        body.setOwnerAgent(ownerAgent);
        body.setInWorkSchedule(inWorkSchedule);
        body.setOutWorkSchedule(outWorkSchedule);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/RoutingPoint")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(RoutingPointSchema))
                .extract().as(RoutingPoint.class);
    }
    @Step("Редактирование точки маршрута")
    public static RoutingPoint editRoutingPoint(Integer id,String name, Boolean isManagedExternally, String referenceId, String longitude, String latitude, Boolean isTransferPoint, Ref ownerAgent, Ref inWorkSchedule, Ref outWorkSchedule){
        RoutingPoint body=new RoutingPoint();
        body.setName(name);
        body.setIsManagedExternally(isManagedExternally);
        body.setLatitude(latitude);
        body.setLongitude(longitude);
        body.setIsTransferPoint(isTransferPoint);
        body.setOwnerAgent(ownerAgent);
        body.setInWorkSchedule(inWorkSchedule);
        body.setOutWorkSchedule(outWorkSchedule);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put ("/entity/AUTO3N/RoutingPoint/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(RoutingPointSchema))
                .extract().as(RoutingPoint.class);
    }
    @Step("Удаление точки маршрута")
    public static RoutingPoint deleteRoutingPoint(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete ("/entity/AUTO3N/RoutingPoint/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(RoutingPointSchema))
                .extract().as(RoutingPoint.class);
    }
}
