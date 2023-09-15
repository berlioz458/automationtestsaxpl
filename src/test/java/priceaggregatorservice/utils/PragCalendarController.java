package priceaggregatorservice.utils;

import helpers.ListInfo;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import priceaggregatorservice.model.Calendar;
import priceaggregatorservice.model.CalendarSpecialDay;
import priceaggregatorservice.model.WorkDays;
import java.io.File;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplate;
import static priceaggregatorservice.spec.PragApiSpecs.*;
import static io.restassured.RestAssured.given;

public class PragCalendarController {
    public static File CalendarSchema= new File("src/test/java/priceaggregatorservice/schemas/Calendar.json");
    public static File CalendarListSchema= new File("src/test/java/priceaggregatorservice/schemas/CalendarList.json");
    @Step("Получение списка календарей")
    public static ListInfo<Calendar> getCalendarList(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Calendar")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(CalendarListSchema))
                .extract()
                .as(new TypeRef<ListInfo<Calendar>>() {
                });
    }
    @Step("Получение списка календарей по парметру")
    public static ListInfo<Calendar> getCalendarListByParams(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/Calendar")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(CalendarListSchema))
                .extract()
                .as(new TypeRef<ListInfo<Calendar>>() {
                });
    }
    @Step("Получение календаря по айди")
    public static Calendar getCalendarById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Calendar/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(CalendarSchema))
                .extract()
                .as(Calendar.class);
    }
    @Step("Создание календаря")
    public static Calendar createCalendar(String name, Boolean isManagedExternally, List<CalendarSpecialDay> calendarSpecialDays, WorkDays workDays){
        Calendar body=new Calendar();
        body.setName(name);
        body.setIsManagedExternally(isManagedExternally);
        body.setCalendarSpecialDays(calendarSpecialDays);
        body.setWorkDays(workDays);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/Calendar")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(CalendarSchema))
                .extract().as(Calendar.class);
    }
    @Step("Редактирование календаря")
    public static Calendar editCalendar(Integer id,String name, Boolean isManagedExternally, List<CalendarSpecialDay> calendarSpecialDays, WorkDays workDays){
        Calendar body=new Calendar();
        body.setName(name);
        body.setIsManagedExternally(isManagedExternally);
        body.setCalendarSpecialDays(calendarSpecialDays);
        body.setWorkDays(workDays);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put ("/entity/AUTO3N/Calendar/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(CalendarSchema))
                .extract().as(Calendar.class);
    }
    @Step("Удаление календаря")
    public static Calendar deleteCalendar(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete ("/entity/AUTO3N/Calendar/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(CalendarSchema))
                .extract().as(Calendar.class);
    }

}
