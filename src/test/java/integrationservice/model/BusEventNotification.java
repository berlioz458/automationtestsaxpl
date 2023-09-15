package integrationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class BusEventNotification {
    private String id;
    private String event;
    private String partyCode;
    private String system;
    private String createdAt;
    private String loggedAt;
    private String action;
    private Integer ownerAgentId;
    private Object userId;
    private String objectGuid;
    private Integer objectId;
    private String objectType;
    private Integer objectVersion;
    private String realmCode;
    private Boolean isRead;
    private Boolean isProcessed;
}
