package priceaggregatorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkDays {
    private Boolean mo;
    private Boolean tu;
    private Boolean we;
    private Boolean th;
    private Boolean fr;
    private Boolean sa;
    private Boolean su;
    public void WorkDaysFiveDays(){
        this.mo=this.tu=this.we=this.th=this.fr=true;
        this.sa=this.su=false;
    }
    public void WorkDaysSixDays(){
        this.mo=this.tu=this.we=this.th=this.fr=this.sa=true;
        this.su=false;
    }
    public void WorkDaysSevenDays(){
        this.mo=this.tu=this.we=this.th=this.fr=this.sa=this.su=true;

    }
}

