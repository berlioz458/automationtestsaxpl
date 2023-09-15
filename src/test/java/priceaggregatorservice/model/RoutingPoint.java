package priceaggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.Ref;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class RoutingPoint extends Entity {
    private String name;
    private Boolean isManagedExternally;
    private String referenceId;
    private String longitude;
    private String latitude;
    private Boolean isTransferPoint;
    private Ref ownerAgent;
    private Ref inWorkSchedule;
    private Ref outWorkSchedule;

}

