package integrationservice.utils;

import integrationservice.model.Currency;
import integrationservice.model.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static helpers.CustomAllureListener.withCustomTemplate;

import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationCurrencyController {
    @Step("Получение списка валют")
    public static Response getCurrency(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Currency")
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }
    @Step("Получение валюты по параметру")
    public static Response getCurrency(String params, String value){
        // добавить возможность сделать запрос с лимитом и с конкретной валютой
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/Currency")
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }
    @Step("Создание валюты")
    public static Currency createCurrency(String name, String isoAlfa, String isoNumber){
        Currency body=new Currency();
        body.setName(name);
        body.setIsoAlfa(isoAlfa);
        body.setIsoNumber(isoNumber);
        body.setCompany(null);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/Currency")
                .then()
                .spec(success_responseSpec)
                .extract().as(Currency.class);
    }


}
