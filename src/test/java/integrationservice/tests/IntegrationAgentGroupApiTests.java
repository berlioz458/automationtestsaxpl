package integrationservice.tests;

import helpers.ListInfo;
import helpers.Ref;
import integrationservice.model.AgentGroup;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static integrationservice.utils.IntegrationAgentGroupController.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationAgentGroupApiTests {
    //Необходимо либо чистить группы, либо менять агентов
    Ref ownerAgentId= new Ref("Agent",10054);
    String name="Autotest ";
    List<Ref> agents=new ArrayList<>();
    Ref agent1= new Ref("Agent",10139);
    Ref agent2= new Ref("Agent",10158);
    Ref agent3= new Ref("Agent",10193);
    Ref agent4= new Ref("Agent",10089);
    Ref agent5= new Ref("Agent",10096);
    Ref agent6= new Ref("Agent",10098);
    Ref agent7= new Ref("Agent",10109);

    @Description("Get list agent group")
    @Test
    void successGetListAgentGroup() {
        ListInfo<AgentGroup> listAgentGroup = getListAgentGroup();
        assertThat(listAgentGroup).isNotNull();
    }

    @Description("Get agent group by id")
    @Test
    void successGetAgentGroupById() {
        Integer expectedId = 2;
        AgentGroup agentGroup = getAgentGroupById(expectedId);
        assertThat(agentGroup).isNotNull();
        assertThat(agentGroup.getId()).isEqualTo(expectedId);
    }
    @Description("Create agent group")
    @Test
    void successCreateAgentGroup(){
        agents.add(agent1);
        agents.add(agent2);
        name=name + java.time.LocalDateTime.now();
        AgentGroup agentGroup= createAgentGroup(ownerAgentId,name,agents);
        assertThat(agentGroup.getName()).isEqualTo(name);
        //assertThat(agentGroup.getOwnerAgent()).isEqualTo(ownerAgentId);
        // add check agents
    }
    @Description("Edit agent group by name")
    @Test
    void successEditAgentGroupByName(){
        name=name + java.time.LocalDateTime.now();
        String nameChange="Autotest";
        agents.add(agent3);
        agents.add(agent4);
        AgentGroup agentGroup= createAgentGroup(ownerAgentId,name, agents);
        //edit by id
        AgentGroup agentGroupEdit= changeAgentGroup(agentGroup.getId(),ownerAgentId,nameChange, agents);
        assertThat(agentGroupEdit.getName()).isEqualTo(nameChange);
    }
    @Description("Edit agent group by name")
    @Test
    void successEditAgentGroupAddAgent(){
        name=name + java.time.LocalDateTime.now();
        agents.add(agent5);
        agents.add(agent6);
        AgentGroup agentGroup= createAgentGroup(ownerAgentId,name, agents);
        //edit by id
        agents.add(agent7);
        AgentGroup agentGroupEdit= changeAgentGroup(agentGroup.getId(),ownerAgentId,name, agents);
        //add check agents
        assertThat(agentGroupEdit).isNotNull();
    }
    @Description("Delete agent group")
    @Test
    void successDeleteAgentGroup(){
        name=name + java.time.LocalDateTime.now();
        AgentGroup agentGroup= createAgentGroup(ownerAgentId,name,agents);
        //delete by id
        AgentGroup agentGroupDelete= deleteAgentGroup(agentGroup.getId());
        assertThat(agentGroupDelete).isNotNull();
        assertThat(agentGroupDelete.getId()).isEqualTo(null);
    }
}
