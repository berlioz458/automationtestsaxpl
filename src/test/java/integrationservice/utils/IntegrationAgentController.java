package integrationservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import helpers.ListInfo;
import integrationservice.model.Agent;
import integrationservice.model.AgentRequest;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import helpers.Ref;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationAgentController {
    public static File AgentSchema= new File("src/test/java/integrationservice/schemas/Agent.json");
    public static File AgentListSchema= new File("src/test/java/integrationservice/schemas/AgentList.json");

    @Step("Получение списка агентов")
    public static ListInfo<Agent> getAgentList(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Agent")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentListSchema))
                .extract().as(new TypeRef<ListInfo<Agent>>() {
                });
    }
    @Step("Получение списка по парметру")
    public static ListInfo<Agent> getAgent(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/Agent")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentListSchema))
                .extract().as(new TypeRef<ListInfo<Agent>>() {
                });
    }
    @Step("Получение агента по id")
    public static Agent getAgentById(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Agent/"+id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentSchema))
                .extract().as(Agent.class);
    }
   @Step("Создание агента")
    public static Agent createAgent(String name, String contactPhone, Boolean isRegistrationDisabled, String timeZone, Ref company, Ref parentAgent, List<Ref> agentTags) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setName(name);
        body.setContactPhone(contactPhone);
        body.setIsRegistrationDisabled(isRegistrationDisabled);
        body.setTimeZone(timeZone);
        body.setCompany(company);
        body.setParentAgent(parentAgent);
        body.setAgentTags(agentTags);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
         return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(jsonString)
                .when()
                .post ("/entity/AUTO3N/Agent")
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentSchema))
                .extract().as(Agent.class);
    }

    @Step("Удаление агента")
    public static Agent deleteAgent(Integer id){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .delete("/entity/AUTO3N/Agent/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Agent.class);
    }
    @Step("Редактирование наименования агента")
    public static Agent changeAgentName(Integer id, String name, List<Ref> agentTags, Ref company) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setAgentTags(agentTags);
        body.setCompany(company);
        body.setName(name);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(jsonString)
                .when()
                .put("/entity/AUTO3N/Agent/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentSchema))
                .extract().as(Agent.class);
    }
    @Step("Редактирование телефона агента")
    public static Agent changeAgentPhone(Integer id, String phone, List<Ref> agentTags, Ref company) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setAgentTags(agentTags);
        body.setCompany(company);
        body.setContactPhone(phone);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(jsonString)
                .when()
                .put("/entity/AUTO3N/Agent/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentSchema))
                .extract().as(Agent.class);
    }
    @Step("Редактирование временной зоны агента")
    public static Agent changeAgentTimeZone(Integer id, String timeZone, List<Ref> agentTags, Ref company) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setAgentTags(agentTags);
        body.setCompany(company);
        body.setTimeZone(timeZone);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(jsonString)
                .when()
                .put("/entity/AUTO3N/Agent/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentSchema))
                .extract().as(Agent.class);
    }
    @Step("Удалить теги")
    public static Agent deleteTagFromAgent(Integer id,  Ref company) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setCompany(company);
        List<Ref> agentTags = new ArrayList<>();
        body.setAgentTags(agentTags);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(jsonString)
                .when()
                .put("/entity/AUTO3N/Agent/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .extract().as(Agent.class);
    }
    @Step("Отключение регистрации")
    public static Agent disabledRegistrationForAgent(Integer id,  Ref company, List<Ref> agentTags) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setIsRegistrationDisabled(true);
        body.setCompany(company);
        body.setAgentTags(agentTags);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .body(jsonString)
                .when()
                .put("/entity/AUTO3N/Agent/"+ id.toString())
                .then()
                .spec(success_responseSpec)
                .body(JsonSchemaValidator.matchesJsonSchema(AgentSchema))
                .extract().as(Agent.class);
    }
}
