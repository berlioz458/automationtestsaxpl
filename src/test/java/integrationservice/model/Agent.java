package integrationservice.model;

import com.fasterxml.jackson.annotation.*;
import helpers.Entity;
import helpers.Ref;
import lombok.*;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)

public class Agent extends Entity {
    private String name;
    private String contactPhone;
    private Boolean isRegistrationDisabled;
    private String timeZone;
    //обязательное поле
    private Ref company;
    private Ref parentAgent;
    //обязательное поле
    private List<AgentTag> agentTags;
    }
