package integrationservice.utils;

import integrationservice.model.Company;
import integrationservice.model.Currency;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import helpers.Ref;

import static helpers.CustomAllureListener.withCustomTemplate;

import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationCurrencyController {
    @Step("Получение списка валют")
    public static Response getCurrencyList(){
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
    @Step("Получение валюты по id")
    public static Currency getCurrencyById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Currency/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Currency.class);
    }

    @Step("Создание валюты")
    public static Currency createCurrency(String name, String isoAlfa, String isoNumber){
        Currency body=new Currency();
        body.setName(name);
        body.setIsoAlfa(isoAlfa);
        body.setIsoNumber(isoNumber);
        body.setCompany(new Ref("id",1));
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
    @Step("Удаление валюты")
    public static Currency deleteCurrency(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete("/entity/AUTO3N/Currency/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Currency.class);
    }
    @Step("Редактирование наименования валюты")
    public static Currency changeCurrencyName(Integer id, String name){
        Currency body=new Currency();
        body.setName(name);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/Currency/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Currency.class);

    }
}
