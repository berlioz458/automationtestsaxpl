package integrationservice.utils;

import integrationservice.model.AgentGroup;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationUserApiSpecs.success_request;
import static integrationservice.spec.IntegrationUserApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationAgentGroupController {
//    @Step("Создание группы агентов")

    @Step("Получение группы агентов по идентификатору")
    public static AgentGroup getAgentGroupById(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/AgentGroup/" + id.toString())
                .then()
                .spec(success_responseSpec)
                .log().all()
                .extract().as(AgentGroup.class);
    }

//    @Step("Изменение группы агентов")
//
//    @Step("Удаление группы агентов")

    @Step("Получение списка групп агентов без фильтрации")
    public static Response getListAgentGroup() {
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/AgentGroup")
                .then()
                .spec(success_responseSpec)
                .log().all()
                .extract().response();
    }
}
