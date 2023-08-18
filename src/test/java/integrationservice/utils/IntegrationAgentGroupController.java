package integrationservice.utils;

import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.AgentGroup;
import integrationservice.spec.IntegrationCurrencyApiSpecs;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationUserApiSpecs.success_request;
import static integrationservice.spec.IntegrationUserApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationAgentGroupController {
    public static File AgentGroupSchema= new File("src/test/java/integrationservice/schemas/AgentGroup.json");
    public static File AgentGroupListSchema= new File("src/test/java/integrationservice/schemas/AgentGroupList.json");
    @Step("Создание группы агентов")
    public static AgentGroup createAgentGroup(Ref ownerAgentId, String name, List<Ref> agents){
        AgentGroup body=new AgentGroup();
        body.setOwnerAgent(ownerAgentId);
        body.setName(name);
        body.setAgents(agents);
        return given()
            .filter(withCustomTemplate())
            .spec(IntegrationCurrencyApiSpecs.success_request)
            .body(body)
            .when()
            .post ("/entity/AUTO3N/AgentGroup")
            .then()
            .spec(IntegrationCurrencyApiSpecs.success_responseSpec)
            .body(JsonSchemaValidator.matchesJsonSchema(AgentGroupListSchema))
            .extract().as(AgentGroup.class);
}
    @Step("Получение группы агентов по идентификатору")
    public static AgentGroup getAgentGroupById(Integer id) {
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/AgentGroup/" + id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentGroupSchema))
                .extract().as(AgentGroup.class);
    }

    @Step("Изменение группы агентов")
    public static AgentGroup changeAgentGroup(Integer id, Ref ownerAgentId, String name, List<Ref>agents){
        AgentGroup body=new AgentGroup();
        body.setName(name);
        body.setOwnerAgent(ownerAgentId);
        body.setAgents(agents);
        return given()
                .filter(withCustomTemplate())
                .spec(IntegrationCurrencyApiSpecs.success_request)
                .body(body)
                .when()
                .put("/entity/AUTO3N/AgentGroup/"+ id.toString())
                .then()
                .spec(IntegrationCurrencyApiSpecs.success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentGroupSchema))
                .extract().as(AgentGroup.class);
    }

    @Step("Удаление группы агентов")
    public static AgentGroup deleteAgentGroup(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(IntegrationCurrencyApiSpecs.success_request)
                .when()
                .delete("/entity/AUTO3N/AgentGroup/"+ id.toString())
                .then()
                .spec(IntegrationCurrencyApiSpecs.success_responseSpec)
                .extract().as(AgentGroup.class);
    }

    @Step("Получение списка групп агентов без фильтрации")
    public static ListInfo<AgentGroup> getListAgentGroup() {
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/AgentGroup")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentGroupListSchema))
                .extract().as(new TypeRef<ListInfo<AgentGroup>>() {
                });
    }
}
