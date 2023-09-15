package priceaggregatorservice.utils;

import helpers.ListInfo;
import helpers.Ref;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import priceaggregatorservice.model.*;
import java.io.File;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.given;
import static priceaggregatorservice.spec.PragApiSpecs.success_request;
import static priceaggregatorservice.spec.PragApiSpecs.success_responseSpec;

public class PragWorkScheduleController {
    public static File WorkScheduleSchema= new File("src/test/java/priceaggregatorservice/schemas/WorkSchedule.json");
    public static File WorkScheduleListSchema= new File("src/test/java/priceaggregatorservice/schemas/WorkScheduleList.json");
    @Step("Получение списка расписаний работы")
    public static ListInfo<WorkSchedule> getWorkScheduleList(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/WorkSchedule")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(WorkScheduleListSchema))
                .extract()
                .as(new TypeRef<ListInfo<WorkSchedule>>() {
                });
    }
    @Step("Получение списка расписаний работы по парметру")
    public static ListInfo<WorkSchedule> getWorkScheduleListByParams(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/WorkSchedule")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(WorkScheduleListSchema))
                .extract()
                .as(new TypeRef<ListInfo<WorkSchedule>>() {
                });
    }
    @Step("Получение расписания работы по айди")
    public static WorkSchedule getWorkScheduleById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/WorkSchedule/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(WorkScheduleSchema))
                .extract()
                .as(WorkSchedule.class);
    }
    @Step("Создание расписания работы")
    public static WorkSchedule createWorkSchedule(String name, Boolean isManagedExternally, String delay, String timeZone, Ref ownerAgent,Ref calendar,List<WorkScheduleRegularPatternItem> workScheduleRegularPatternItems,List<WorkScheduleDayPatternItem> workScheduleDayPatternItems,List<WorkScheduleDatePatternItem> workScheduleDatePatternItems){
        WorkSchedule body=new WorkSchedule();
        body.setName(name);
        body.setIsManagedExternally(isManagedExternally);
        body.setDelay(delay);
        body.setTimeZone(timeZone);
        body.setOwnerAgent(ownerAgent);
        body.setCalendar(calendar);
        body.setWorkScheduleRegularPatternItems(workScheduleRegularPatternItems);
        body.setWorkScheduleDayPatternItems(workScheduleDayPatternItems);
        body.setWorkScheduleDatePatternItems(workScheduleDatePatternItems);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/WorkSchedule")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(WorkScheduleSchema))
                .extract().as(WorkSchedule.class);
    }
    @Step("Редактирование расписания работы")
    public static WorkSchedule editWorkSchedule(Integer id, String name, Boolean isManagedExternally, String delay, String timeZone, Ref ownerAgent, Ref calendar, List<WorkScheduleRegularPatternItem> workScheduleRegularPatternItems, List<WorkScheduleDayPatternItem> workScheduleDayPatternItems, List<WorkScheduleDatePatternItem> workScheduleDatePatternItems){
        WorkSchedule body=new WorkSchedule();
        body.setName(name);
        body.setIsManagedExternally(isManagedExternally);
        body.setDelay(delay);
        body.setTimeZone(timeZone);
        body.setOwnerAgent(ownerAgent);
        body.setCalendar(calendar);
        body.setWorkScheduleRegularPatternItems(workScheduleRegularPatternItems);
        body.setWorkScheduleDayPatternItems(workScheduleDayPatternItems);
        body.setWorkScheduleDatePatternItems(workScheduleDatePatternItems);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put ("/entity/AUTO3N/WorkSchedule/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(WorkScheduleSchema))
                .extract().as(WorkSchedule.class);
    }
    @Step("Удаление расписания работы")
    public static WorkSchedule deleteWorkSchedule(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete ("/entity/AUTO3N/WorkSchedule/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(WorkScheduleSchema))
                .extract().as(WorkSchedule.class);
    }
}
