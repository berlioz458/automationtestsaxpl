package priceaggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class WorkScheduleDayPatternItem {
    private Integer day;
    private Boolean isWorkDay;
    private String openAt;
    private String workInterval;
    public WorkScheduleDayPatternItem( Integer day,Boolean isWorkDay,String openAt,String workInterval){
        this.isWorkDay=isWorkDay;
        this.day=day;
        this.openAt=openAt;
        this.workInterval=workInterval;
    }
}
