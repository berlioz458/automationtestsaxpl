package priceaggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Entity;
import helpers.Ref;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class WorkSchedule extends Entity {
    private String name;
    private Boolean isManagedExternally;
    private String clientData;
    private String delay;
    private String timeZone;
    private Ref ownerAgent;
    private Ref calendar;
    private List<WorkScheduleRegularPatternItem> workScheduleRegularPatternItems;
    private List<WorkScheduleDayPatternItem> workScheduleDayPatternItems;
    private List<WorkScheduleDatePatternItem> workScheduleDatePatternItems;
    private List<Object> workSchedulePlanItems;
    private List<Object>workScheduleOverrides;

}
