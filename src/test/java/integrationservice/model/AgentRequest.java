package integrationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import helpers.Entity;
import helpers.Ref;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "Agent")
public class AgentRequest extends Entity {
    private String name;
    private String contactPhone;
    private Boolean isRegistrationDisabled;
    private String timeZone;
    private Ref company;
    private Ref parentAgent;
    private List<Ref> agentTags;
}
