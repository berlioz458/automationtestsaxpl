package integrationservice.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import integrationservice.model.Agent;
import integrationservice.model.AgentRequest;
import integrationservice.model.AgentTag;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import helpers.Ref;

import java.util.ArrayList;
import java.util.List;
import static helpers.CustomAllureListener.withCustomTemplate;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_request;
import static integrationservice.spec.IntegrationCurrencyApiSpecs.success_responseSpec;
import static io.restassured.RestAssured.given;

public class IntegrationAgentController {
    //TODO: Дописать агенттеги
    @Step("Получение списка агентов")
    public static Response getAgentList(){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .when()
                .get("/entity/AUTO3N/Agent")
                .then()
                .spec(success_responseSpec)
                .extract().response();
    }
    @Step("Получение списка по парметру")
    public static Response getAgent(String params, String value){
        return given()
                .filter(withCustomTemplate())
                .spec(success_request)
                .param(params,value)
                .when()
                .get("/entity/AUTO3N/Agent")
                .then()
                .spec(success_responseSpec)
                .extract().response();
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
                .log().body(true)
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
    public static Agent changeAgentName(Integer id, String name, List<Ref> agentTags, Ref company, Ref parentAgent) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setName(name);
        body.setAgentTags(agentTags);
        body.setCompany(company);
        body.setParentAgent(parentAgent);
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
    @Step("Отключить агента для гцс")
    public static Agent disableAgentForGcs(Integer id, String name, Ref company) throws JsonProcessingException {
        AgentRequest body=new AgentRequest();
        body.setIsRegistrationDisabled(true);
        body.setCompany(company);
        body.setName(name);
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
}
