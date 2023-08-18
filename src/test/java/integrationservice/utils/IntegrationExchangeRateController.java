package integrationservice.utils;

import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.ExchangeRate;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationExchangeRateController {
    public static File ExchangeRateSchema= new File("src/test/java/integrationservice/schemas/ExchangeRate.json");
    public static File ExchangeRateListSchema= new File("src/test/java/integrationservice/schemas/ExchangeRateList.json");
    @Step("Получение списка курсов валют")
    public static ListInfo<ExchangeRate> getExchangeRateList(Integer idExchangeRateProfile){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/ExchangeRateProfile/"+idExchangeRateProfile+"/ExchangeRate")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateListSchema))
                .extract().as(new TypeRef<ListInfo<ExchangeRate>>() {
                });
    }
    @Step("Получение курса валюты по параметру как респонс")
    public static ListInfo<ExchangeRate> getExchangeRateAsListInfo(Integer idExchangeRateProfile, String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/ExchangeRateProfile/"+idExchangeRateProfile+"/ExchangeRate")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateListSchema))
                .extract().as(new TypeRef<ListInfo<ExchangeRate>>() {
                });
    }

    @Step("Получение курса валюты по id")
    public static ExchangeRate getExchangeRateById(Integer idExchangeRateProfile, Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/ExchangeRateProfile/"+idExchangeRateProfile+"/ExchangeRate/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateSchema))
                .extract().as(ExchangeRate.class);
    }

    @Step("Создание курса валюты")
    public static ExchangeRate createExchangeRate(Integer idExchangeRateProfile, Double value){
        ExchangeRate body=new ExchangeRate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date=formatter.format(Calendar.getInstance().getTime());
        body.setDate(date);
        body.setValue(value);
        body.setNominal(1);
        body.setExchangeRateProfile(new Ref("ExchangeRateProfile",idExchangeRateProfile));
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/ExchangeRateProfile/"+idExchangeRateProfile+"/ExchangeRate")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateSchema))
                .extract().as(ExchangeRate.class);
    }
    @Step("Удаление курса валюты")
    public static ExchangeRate deleteExchangeRate(Integer idExchangeRateProfile,Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete("/entity/AUTO3N/ExchangeRateProfile/"+idExchangeRateProfile+"/ExchangeRate"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(ExchangeRate.class);
    }
    @Step("Редактирование курса валюты")
    public static ExchangeRate changeExchangeRate(Integer idExchangeRateProfile, Integer id,Double value){
        ExchangeRate body=new ExchangeRate();
        body.setDate(java.time.LocalDateTime.now().toString());
        body.setValue(value);
        body.setNominal(1);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/ExchangeRateProfile/"+idExchangeRateProfile+"/ExchangeRate"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateSchema))
                .extract().as(ExchangeRate.class);

    }
    // service method need add
    @Step("Получение профиля курса валюты по двум валютам")
    public static ExchangeRate getExchangeRateCurrencyToCurrency(Integer fromCurrencyId, Integer toCurrencyId){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/service/AUTO3N/ExchangeRate/"+fromCurrencyId+"/"+toCurrencyId+"/get")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateSchema))
                .extract().as(ExchangeRate.class);
    }
}
