package integrationservice.utils;

import helpers.Ref;
import integrationservice.model.Company;
import integrationservice.model.Currency;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationCompanyController {
    @Step("Получение списка компаний")
    public static Response getCompanyList(){
    return given()
            .filter(withCustomTemplate())
            .spec(success_request)
            .when()
            .get("/entity/AUTO3N/Company")
            .then()
            .spec(success_responseSpec)
            .extract().response();
}
    @Step("Получение компании по параметру")
    public static Response getCompany(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/Company")
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }
    @Step("Получение компании по id")
    public static Company getCompanyById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Company/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Company.class);
    }

    @Step("Создание компании")
    public static Company createCompany(String name, Ref currency){
        Company body=new Company();
        body.setName(name);
        body.setBaseCurrency(currency);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .post ("/entity/AUTO3N/Company")
                .then()
                .spec(success_responseSpec)
                .extract().as(Company.class);
    }
    @Step("Удаление компании")
    public static Company deleteCompany(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete("/entity/AUTO3N/Company/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Company.class);
    }
    @Step("Редактирование наименования компании")
    public static Company changeCompanyName(Integer id, String name){
        Company body=new Company();
        body.setName(name);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/Company/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Company.class);

    }
    @Step("Редактирование валюты компании")
    public static Company changeCompanyCurrency(Integer id, Ref currency){
        Company body=new Company();
        body.setBaseCurrency(currency);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/Company/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Company.class);

    }
    @Step("Редактирование наименования и валюты компании")
    public static Company changeCompanyNameAndCurrency(Integer id, String name, Ref currency){
        Company body=new Company();
        body.setName(name);
        body.setBaseCurrency(currency);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/Company/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Company.class);

    }
}
