package bus.orderservice.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Document extends Entity {
    private String name;
    private final String parentObjectType = "Counteragent";
    private final String type = "html";
    private String jsonBody;
    private final Integer ownerAgentId = 10554;
    private final Integer parentObjectId = 39118;
    private final Boolean deleted = false;
}
