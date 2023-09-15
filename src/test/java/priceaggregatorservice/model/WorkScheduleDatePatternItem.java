package priceaggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class WorkScheduleDatePatternItem {
    private String date;
    private Boolean isWorkDay;
    private String openAt;
    private String workInterval;
    public WorkScheduleDatePatternItem( String date,Boolean isWorkDay,String openAt,String workInterval){
        this.isWorkDay=isWorkDay;
        this.date=date;
        this.openAt=openAt;
        this.workInterval=workInterval;
    }

}
