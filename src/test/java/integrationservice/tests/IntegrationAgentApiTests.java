package integrationservice.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.Agent;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static integrationservice.utils.IntegrationAgentController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationAgentApiTests {
    Integer id=10396;
    String name= "Autotest ";
    String contactPhone="8 (555) 555-55-55";
    boolean isRegistrationDisabled=false;
    String timeZone="Asia/Krasnoyarsk";
    Ref company=new Ref("Company",1003);
    Ref parentAgent;
    List<Ref> agentTags= new ArrayList<>();

    @Description("List agent")
    @Test
    void successGetListAgent() {
        ListInfo<Agent> agentList= getAgentList();
        assertThat(agentList).isNotNull();
    }
    @Description("Agent by id")
    @Test
    void successGetAgentById() {
        Agent agent= getAgentById(id);
        assertThat(agent).isNotNull();
        assertThat(agent.getId()).isEqualTo(id);

    }
    @Description("Agent by name")
    @Test
    void successGetAgentByName() {
        ListInfo<Agent> agent= getAgent("q","{\"$and\": [{\"name\":\"Москва Агентство Авто 3Н\"}]}");
        assertThat(agent).isNotNull();
    }
    @Description("Create agent")
    @Test
    void successCreateAgent() throws JsonProcessingException {
        name=name + java.time.LocalDateTime.now();
        Ref agentTag = new Ref(null,10001);
        agentTags.add(agentTag);
        parentAgent= new Ref("Agent",10054);
        Agent agent= createAgent(name, contactPhone, isRegistrationDisabled, timeZone, company, parentAgent, agentTags);
        System.out.println(agent);
        assertThat(agent.getName()).isEqualTo(name);
        assertThat(agent.getContactPhone()).isEqualTo(contactPhone);
        assertThat(agent.getIsRegistrationDisabled()).isEqualTo(isRegistrationDisabled);
        assertThat(agent.getTimeZone()).isEqualTo(timeZone);
        assertThat(agent.getParentAgent().getId()).isEqualTo(10054);
        Agent agentDisable =deleteTagFromAgent(agent.getId(),company);
    }
    @Description("Create children agent")
    @Test
    void successCreateChildrenAgent() throws JsonProcessingException {
        name=name + java.time.LocalDateTime.now();
        Ref agentTag = new Ref(null,2);
        agentTags.add(agentTag);
        parentAgent= new Ref("Agent",1100);
        Agent agent= createAgent(name, contactPhone, isRegistrationDisabled, timeZone, company, parentAgent, agentTags);
        assertThat(agent.getName()).isEqualTo(name);
        assertThat(agent.getContactPhone()).isEqualTo(contactPhone);
        assertThat(agent.getIsRegistrationDisabled()).isEqualTo(isRegistrationDisabled);
        assertThat(agent.getTimeZone()).isEqualTo(timeZone);
        assertThat(agent.getParentAgent().getId()).isEqualTo(1100);
        Agent agentDisable =deleteTagFromAgent(agent.getId(),company);
    }
    @Description("Edit agent by name")
    @Test
    void successChangeCompanyName() throws JsonProcessingException {
        // create
        name=name + java.time.LocalDateTime.now();
        Ref agentTag = new Ref(null,10001);
        agentTags.add(agentTag);
        parentAgent= null;
        String nameChange="Autotest Edit "+ java.time.LocalDateTime.now();
        Agent agent= createAgent(name, contactPhone, isRegistrationDisabled, timeZone, company, parentAgent, agentTags);
        //edit by id
        Agent agentEdit= changeAgentName(agent.getId(),nameChange,agentTags,company);
        assertThat(agentEdit.getName()).isEqualTo(nameChange);
        assertThat(agentEdit.getChangedAt()).isNotNull();
        assertThat(agentEdit.getChangedByUser()).isNotNull();
        Agent agentDisable =deleteTagFromAgent(agent.getId(),company);
    }
    @Description("Edit agent by phone")
    @Test
    void successChangeCompanyPhone() throws JsonProcessingException {
        // create
        name=name + java.time.LocalDateTime.now();
        Ref agentTag = new Ref(null,10001);
        agentTags.add(agentTag);
        parentAgent= null;
        String changePhone = "+7777777777777";
        Agent agent= createAgent(name, contactPhone, isRegistrationDisabled, timeZone, company, parentAgent, agentTags);
        //edit by id
        Agent agentEdit= changeAgentPhone(agent.getId(),changePhone,agentTags,company);
        assertThat(agentEdit.getName()).isEqualTo(changePhone);
        assertThat(agentEdit.getChangedAt()).isNotNull();
        assertThat(agentEdit.getChangedByUser()).isNotNull();
        Agent agentDisable =deleteTagFromAgent(agent.getId(),company);
    }
    @Description("Edit agent by time zone")
    @Test
    void successChangeCompanyTimeZone() throws JsonProcessingException {
        // create
        name=name + java.time.LocalDateTime.now();
        Ref agentTag = new Ref(null,10001);
        agentTags.add(agentTag);
        parentAgent= null;
        String timeZone="Asia/Irkutsk";
        Agent agent= createAgent(name, contactPhone, isRegistrationDisabled, timeZone, company, parentAgent, agentTags);
        //edit by id
        Agent agentEdit= changeAgentTimeZone(agent.getId(),timeZone,agentTags,company);
        assertThat(agentEdit.getName()).isEqualTo(timeZone);
        assertThat(agentEdit.getChangedAt()).isNotNull();
        assertThat(agentEdit.getChangedByUser()).isNotNull();
        Agent agentDisable =deleteTagFromAgent(agent.getId(),company);
    }
    @Description("Delete agent")
    @Test
    void successDeleteAgent() throws JsonProcessingException {
        // create
        name=name + java.time.LocalDateTime.now();
        Agent agent= createAgent(name, contactPhone, isRegistrationDisabled, timeZone, company, parentAgent, agentTags);
        //delete by id
        Agent agentDelete= deleteAgent(agent.getId());
        assertThat(agentDelete).isNotNull();
        assertThat(agentDelete.getId()).isEqualTo(null);
    }
}
