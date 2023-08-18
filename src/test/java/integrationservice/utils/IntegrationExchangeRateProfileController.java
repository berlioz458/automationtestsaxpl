package integrationservice.utils;

import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.ExchangeRateProfile;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;


import java.io.File;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationExchangeRateProfileController {
    public static File ExchangeRateProfileSchema= new File("src/test/java/integrationservice/schemas/ExchangeRateProfile.json");
    public static File ExchangeRateProfileListSchema= new File("src/test/java/integrationservice/schemas/ExchangeRateProfileList.json");
    @Step("Получение списка профилей курсов валют")
    public static ListInfo<ExchangeRateProfile> getExchangeRateProfileList(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/ExchangeRateProfile")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateProfileListSchema))
                .extract().as(new TypeRef<ListInfo<ExchangeRateProfile>>() {
                });
    }
    @Step("Получение профиля курса валюты по параметру")
    public static ListInfo<ExchangeRateProfile>  getExchangeRateProfile(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/ExchangeRateProfile")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateProfileListSchema))
                .extract().as(new TypeRef<ListInfo<ExchangeRateProfile>>() {
                });
    }
    @Step("Получение профиля курса валюты по id")
    public static ExchangeRateProfile getExchangeRateProfileById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/ExchangeRateProfile/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateProfileSchema))
                .extract().as(ExchangeRateProfile.class);
    }

    @Step("Создание профиля курса валюты")
    public static ExchangeRateProfile createExchangeRateProfile(Ref currencyFrom, Ref currencyTo){
        ExchangeRateProfile body=new ExchangeRateProfile();
        body.setSource("manual");
        body.setChangeValue(null);
        body.setNominal(1);
        body.setCompany(null);
        body.setCurrencyFrom(currencyFrom);
        body.setCurrencyTo(currencyTo);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/ExchangeRateProfile")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateProfileSchema))
                .extract().as(ExchangeRateProfile.class);
    }
    @Step("Удаление профиля курса валюты")
    public static ExchangeRateProfile deleteExchangeRateProfile(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete("/entity/AUTO3N/ExchangeRateProfile/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(ExchangeRateProfile.class);
    }
    @Step("Редактирование профиля курса валюты")
    // в админке меняется номинал и источник
    // in integration admin change only nominal and source
    public static ExchangeRateProfile changeExchangeRateProfile(Integer id, String source, Integer nominal){
        ExchangeRateProfile body=new ExchangeRateProfile();
        body.setSource(source);
        body.setNominal(nominal);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/ExchangeRateProfile/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(ExchangeRateProfileSchema))
                .extract().as(ExchangeRateProfile.class);

    }

}
