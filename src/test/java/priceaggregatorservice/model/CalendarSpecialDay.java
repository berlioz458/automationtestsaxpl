package priceaggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class CalendarSpecialDay {
    private Boolean isWorkDay;
    private String date;
    private String note;
    public CalendarSpecialDay( Boolean isWorkDay,String date,String note){
        this.isWorkDay=isWorkDay;
        this.date=date;
        this.note=note;
    }
}
