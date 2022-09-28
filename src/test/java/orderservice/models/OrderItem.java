package orderservice.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import helpers.Ref;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class OrderItem {
    private Integer ownerAgentId;
    private String brand;
    private Integer currentPrice;
    private Integer currentSumTotal;
    private Integer initialAmount;
    private Integer initialPrice;
    private ProvisionPlan initialProvision;
    private Integer initialSumTotal;
    private String marketingData;
    private String name;
    private String oem;
    private Ref status;
    private String workItemNumber;
}
