package integrationservice.tests;

import integrationservice.model.AgentGroup;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static integrationservice.utils.IntegrationAgentGroupController.getAgentGroupById;
import static integrationservice.utils.IntegrationAgentGroupController.getListAgentGroup;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationAgentGroupApiTests {
    @Test
    void successGetListAgentGroup() {
        Response listAgentGroup = getListAgentGroup();
        assertThat(listAgentGroup).isNotNull();
    }

    @Test
    void successGetAgentGroup() {
        Integer expectedId = 5;
        AgentGroup agentGroup = getAgentGroupById(expectedId);
        assertThat(agentGroup).isNotNull();
        assertThat(agentGroup.getId()).isEqualTo(expectedId);
    }
}
